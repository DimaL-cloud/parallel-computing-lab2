package ua.dmytrolutsiuk.calculator;

import lombok.SneakyThrows;

public class BlockingModuloSumCalculator implements ModuloSumCalculator {

    private static final int THREADS_AMOUNT = Runtime.getRuntime().availableProcessors();

    private int result = 0;

    private synchronized void xor(int num) {
        result ^= num;
    }

    @Override
    @SneakyThrows
    public int calculateModuloSum(int[] array) {
        var threads = new Thread[THREADS_AMOUNT];
        int chunkSize = array.length / THREADS_AMOUNT;
        for (int i = 0; i < THREADS_AMOUNT; i++) {
            int start = i * chunkSize;
            int end = (i == THREADS_AMOUNT - 1) ? array.length : (i + 1) * chunkSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (array[j] % DIVISOR == 0) {
                        xor(array[j]);
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return result;
    }
}
