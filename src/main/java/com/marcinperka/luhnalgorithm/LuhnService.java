package com.marcinperka.luhnalgorithm;


import org.springframework.stereotype.Service;

@Service
public class LuhnService {

    /**
     * The method is used to determine the weighted sum of numbers modulo 10 according to Luhn Algorithm.
     * The return of method is used in other methods: {@link #getCheckDigit(String) getCheckDigit} or {@link #validateTheNumber(String) validateTheNumber}.
     *
     * @param digitSequence     - String of digits.
     * @param lastPositionIsEven - Parameter responsible for assigning weights. If true the last position is even(weight 2). If false the last position is odd(weight 1).
     * @return Sum of weighted numbers according to the Luhn Algorithm modulo 10.
     */
    private int processLuhnAlgorithm(String digitSequence, boolean lastPositionIsEven) {
        int sum = 0;
        int iteratedNumber;

        for (int i = digitSequence.length() - 1; i >= 0; i--) {
            iteratedNumber = Integer.parseInt(digitSequence.substring(i, i + 1));
            if (lastPositionIsEven) {
                iteratedNumber *= 2; // if even multiply by 2
                if (iteratedNumber > 9) {
                    iteratedNumber = (iteratedNumber % 10) + 1; // If number has two digits we are going to get second digit and add 1 because for every single number multiplied by 2 we will get the number less than 20.
                }
            }
            sum += iteratedNumber;
            lastPositionIsEven = !lastPositionIsEven;
        }

        return sum % 10 == 0 ? 0: 10-(sum % 10);
    }

    /**
     * Method invoke {@link #processLuhnAlgorithm(String, boolean) processLuhnAlgorithm} method.
     * When calculating the check digit, it is assumed that the last item is even so we set parameter "lastPositionIsOdd" to true.
     *
     * @param digitSequence - String of digits.
     * @return - Check digit.
     */
    public int getCheckDigit(String digitSequence) {
        return processLuhnAlgorithm(digitSequence, true);
    }

    /**
     * Method invoke {@link #processLuhnAlgorithm(String, boolean) processLuhnAlgorithm} method.
     * When validate the check digit, it is assumed that the last item is odd so we set parameter "lastPositionIsOdd" to true.
     *
     * @return The sequence is considered valid if the checksum mod 10 equals to zero.
     */
    public boolean validateTheNumber(String digitSequence) {
        return processLuhnAlgorithm(digitSequence, false) == 0;
    }
}
