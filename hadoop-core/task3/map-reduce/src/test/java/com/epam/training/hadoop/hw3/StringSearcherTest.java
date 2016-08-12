package com.epam.training.hadoop.hw3;

import com.epam.training.hadoop.hw3.utils.StringSearcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class StringSearcherTest {
    private String testIp = "ip1";
    private String testBytes = "40028";
    private String line = testIp + " - - " +
            "[24/Apr/2011:04:06:01 -0400] " +
            "\"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" " +
            "200 " + testBytes + " \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"";

    @Test
    public void getBytesFromLineTest() throws Exception {
        StringSearcher stringSearcher = new StringSearcher(line);
        String aByte = stringSearcher.getBytes();
        assertThat(aByte, is(testBytes));
        String ip = stringSearcher.getIp();
        assertThat(ip, is(testIp));
    }

}