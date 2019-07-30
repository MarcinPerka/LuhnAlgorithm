package com.marcinperka.luhnalgorithm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuhnService {


    private Luhn luhn;

    /**
     * @param digitSequence  - number for which we want get checksum
     * @param orderOfWeights - order of weights, if true - the first item is odd, second even. If false - the first item is even, second odd.
     * @return possible proper control number/checksum
     */
    public int processLuhnAlgorithm(String digitSequence, boolean orderOfWeights) {
        int sum = 0;
        int iteratedNumber;

        for (int i = digitSequence.length() - 1; i >= 0; i--) {
            iteratedNumber = Integer.parseInt(digitSequence.substring(i, i + 1));
            if (orderOfWeights) {
                iteratedNumber *= 2; // if odd multiply by 2
                if (iteratedNumber > 9) {
                    iteratedNumber = (iteratedNumber % 10) + 1; // If number has two digits we are going to get second digit and add 1 because for every single number multiplied by 2 we will get number less than 20.
                }
            }
            sum += iteratedNumber;
            orderOfWeights = !orderOfWeights;
        }

        return sum % 10;

    }

    /**
     * @param digitSequence
     * @return checksum
     */
    public int getCheckDigit(String digitSequence) {
        int result = processLuhnAlgorithm(digitSequence, true);
        return result == 0 ? 0 : 10 - (result % 10);
    }

    /**
     * @param fullCode - digit sequence with check digit on last position
     * @return true if code is valid, false if it is not.
     */
    public boolean verifyFullCode(String fullCode) {
        return processLuhnAlgorithm(fullCode, false) == 0;
    }

    public void setLuhn(Luhn luhn) {
        this.luhn = luhn;
        this.luhn.setFullCode(luhn.getDigitSequence() + getCheckDigit(luhn.getDigitSequence()));
        this.luhn.setValid(verifyFullCode(this.luhn.getFullCode()));
    }

    public Luhn getLuhn() {
        return luhn;
    }
}
