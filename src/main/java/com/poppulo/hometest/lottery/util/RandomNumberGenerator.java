package com.poppulo.hometest.lottery.util;

import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static int[] generateNumber(int upperBound, int length){
        int[] randomNumbers = new int[length];

        for (int i=0; i<length; i++)
            randomNumbers[i] = random.nextInt(upperBound);

        return randomNumbers;
    }
}
