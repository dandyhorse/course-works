package com.epam.hadoop.hw4.mapreduce;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class ReduceTest {
    @Test
    public void test() throws Exception {
        assertThat("a", is(not("b")));
    }
}