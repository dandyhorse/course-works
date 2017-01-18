package com.epam.homework_7.task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class RunnerTest {

    @Test
    public void doCalculation() throws Exception {
        Integer integer = 100 * 100_000;
        Long result = integer.longValue();

        Long aLong = new Runner().doCalculation();

        assertEquals(aLong, result);
    }

}