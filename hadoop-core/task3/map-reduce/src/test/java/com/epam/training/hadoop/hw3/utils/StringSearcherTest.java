package com.epam.training.hadoop.hw3.utils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class StringSearcherTest {


    private String testIp = "ip1";
    private String testBytes = "40028";
    private String testBrowserName = "Mozilla";
    private String line = testIp + " - - " +
            "[24/Apr/2011:04:06:01 -0400] " +
            "\"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" " +
            "200 " + testBytes + " \"-\" \"" + testBrowserName + "/5.0 " +
            "(compatible; YandexImages/3.0; +http://yandex.com/bots)\"";

    private StringSearcher stringSearcher;

    @Before
    public void setUp() throws Exception {
        stringSearcher = new StringSearcher(line);
    }

    @Test
    public void getBytesFromLineTest() throws Exception {
        String aByte = stringSearcher.getBytes();
        assertThat(aByte, is(testBytes));
        String ip = stringSearcher.getIp();
        assertThat(ip, is(testIp));
    }

    @Test
    public void getBrowserNameTest() throws Exception {
        String browserName = stringSearcher.getBrowserName();
        assertThat(browserName, is(testBrowserName));
    }
}