package com.epam.training.hadoop.hw3.mapreduce.utils;

import com.epam.training.hadoop.hw3.mapreduce.mapreduce.utils.StringSearcher;
import org.junit.Before;
import org.junit.Test;

import static com.epam.training.hadoop.hw3.mapreduce.ResourcesForTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class StringSearcherTest {

    private StringSearcher stringSearcher;

    @Before
    public void setUp() throws Exception {
        stringSearcher = new StringSearcher(TEST_LINE);
    }

    @Test
    public void getBytesFromLineTest() throws Exception {
        String aByte = stringSearcher.getBytes();
        assertThat(aByte, is(TEST_BYTES));
        String ip = stringSearcher.getIp();
        assertThat(ip, is(TEST_IP));
    }

    @Test
    public void getBrowserNameTest() throws Exception {
        String browserName = stringSearcher.getBrowserName();
        assertThat(browserName, is(TEST_BROWSER_NAME));
    }
}