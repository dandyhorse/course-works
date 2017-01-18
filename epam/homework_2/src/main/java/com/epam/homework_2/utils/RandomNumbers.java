package com.epam.homework_2.utils;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by User on 01.05.2016.
 */
public class RandomNumbers {

    /**
     * @param lowerBound the origin (inclusive) of each random value
     * @param upperBound the bound (exclusive) of each random value
     * @return an array of pseudorandom {@code int} values,
     * each with the given lower (inclusive) and upper (exclusive)
     */
    public static int[] getSeveralNumbers(int howMany, int lowerBound, int upperBound) {
        Random numbers = new Random();
        IntStream ints = numbers.ints(howMany, lowerBound, upperBound);
        int[] array = ints.toArray();
        ints.close();
        return array;
    }

    /**
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return the next pseudorandom, uniformly distributed {@code int}
     * value between zero (inclusive) and {@code bound}
     */
    public static int getOneNumber(int bound) {
        Random numbers = new Random();
        return numbers.nextInt(bound);
    }
}
