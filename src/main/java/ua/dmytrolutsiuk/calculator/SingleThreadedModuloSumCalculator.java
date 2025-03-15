package ua.dmytrolutsiuk.calculator;

public class SingleThreadedModuloSumCalculator implements ModuloSumCalculator {

    @Override
    public int calculateModuloSum(int[] array) {
        int sum = 0;
        for (int number: array) {
            if (number % DIVISOR == 0) {
                sum += number;
            }
        }
        return sum % MODULO;
    }
}