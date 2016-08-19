package com.epam.hadoop.hw4.mapreduce.utils;

import org.junit.Before;
import org.junit.Test;

import static com.epam.hadoop.hw4.mapreduce.ResourceForTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Anton_Solovev
 * @since 8/19/2016.
 */
public class StringSearcherTest {

    private StringSearcher stringSearcher;

    @Before
    public void setUp() throws Exception {
        stringSearcher = new StringSearcher(TEST_LINE_EXAMPLE);
    }

    @Test
    public void getOSTypeTest() throws Exception {
        String type = stringSearcher.getOSType();
        assertThat(type, is(TEST_OS_TYPE));
    }

    @Test
    public void getCityCodeTest() throws Exception {
        String code = stringSearcher.getCityCode();
        assertThat(code, is(TEST_CITY_CODE));
    }

    @Test
    public void getBidPriceTest() throws Exception {
        String price = stringSearcher.getBidPrice();
        assertThat(price, is(TEST_BID_PRICE));
    }
}