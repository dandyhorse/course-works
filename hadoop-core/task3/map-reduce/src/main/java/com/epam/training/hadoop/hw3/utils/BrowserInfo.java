package com.epam.training.hadoop.hw3.utils;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class BrowserInfo {

    private String browserName;
    private Long browserCount;

    public BrowserInfo(String browserName, Long browserCount) {
        this.browserName = browserName;
        this.browserCount = browserCount;
    }

    public String getBrowserName() {
        return browserName;
    }

    public Long getBrowserCount() {
        return browserCount;
    }

}
