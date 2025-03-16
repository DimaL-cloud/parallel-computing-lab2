package ua.dmytrolutsiuk.calculator;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicModuloSumCalculator implements ModuloSumCalculator {

    private static final int THREADS_AMOUNT = Runtime.getRuntime().availableProcessors();

    private final AtomicInteger totalSum = new AtomicInteger(0);

    @Override
    @SneakyThrows
    public int calculateModuloSum(int[] array) {
        Thread[] threads = new Thread[THREADS_AMOUNT];
        int chunkSize = array.length / THREADS_AMOUNT;
        for (int i = 0; i < THREADS_AMOUNT; i++) {
            int start = i * chunkSize;
            int end = (i == THREADS_AMOUNT - 1) ? array.length : (i + 1) * chunkSize;
            threads[i] = new Thread(() -> {
                int sum = 0;
                for (int j = start; j < end; j++) {
                    if (array[j] % DIVISOR == 0) {
                        sum += array[j];
                    }
                }
                int oldSum;
                do {
                    oldSum = totalSum.get();
                } while (!totalSum.compareAndSet(oldSum, oldSum + sum));
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return totalSum.get() % MODULO;
    }
}
