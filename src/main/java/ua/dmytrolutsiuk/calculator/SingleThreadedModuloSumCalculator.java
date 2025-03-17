package ua.dmytrolutsiuk.calculator;

public class SingleThreadedModuloSumCalculator implements ModuloSumCalculator {

    @Override
    public int calculateModuloSum(int[] array) {
        int result = 0;
        for (int number: array) {
            if (number % DIVISOR == 0) {
                result ^= number;
            }
        }
        return result;
    }
}