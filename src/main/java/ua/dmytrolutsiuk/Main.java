package ua.dmytrolutsiuk;

import lombok.extern.slf4j.Slf4j;
import ua.dmytrolutsiuk.calculator.ModuloSumCalculator;
import ua.dmytrolutsiuk.calculator.SingleThreadedModuloSumCalculator;

import java.util.Random;

@Slf4j
public class Main {

    private static final int ARRAY_SIZE = 10_000_000;
    private static final int RANDOM_BOUND = 10_000;
    private static final int NANO_TO_MILLIS_CONVERSION = 1_000_000;

    private static void printResults(ModuloSumCalculator moduloSumCalculator, int[] array) {
        long startTime = System.nanoTime();
        int sum = moduloSumCalculator.calculateModuloSum(array);
        long endTime = System.nanoTime();
        log.info(
                "{} execution time: {}ms, result: {}",
                moduloSumCalculator.getClass().getSimpleName(),
                (endTime - startTime) / NANO_TO_MILLIS_CONVERSION,
                sum
        );
    }

    private static int[] generateRandomArray(int size) {
        var random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(RANDOM_BOUND);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(ARRAY_SIZE);
        printResults(new SingleThreadedModuloSumCalculator(), array);
    }
}
