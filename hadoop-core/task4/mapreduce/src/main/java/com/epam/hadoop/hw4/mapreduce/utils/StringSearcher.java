package com.epam.hadoop.hw4.mapreduce.utils;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class StringSearcher {

    //indexes from tail
    private static final int BID_PRICE_INDEX = 5;
    private static final int CITY_CODE_INDEX = 17;

    private String[] splitedLine;
    private UserAgent userAgent;

    public StringSearcher(String line) {
        userAgent = new UserAgent(line);
        splitedLine = line.split("\\s");
    }

    public String getBidPrice() {
        return splitedLine[splitedLine.length - BID_PRICE_INDEX];
    }

    public String getOSType() {
        return userAgent.getOperatingSystem().getName();
    }

    public String getCityCode() {
        return splitedLine[splitedLine.length - CITY_CODE_INDEX];
    }

}
