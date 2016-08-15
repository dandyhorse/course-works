package com.epam.training.hadoop.hw3.mapreduce.utils;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class BrowserInfo {

    private String browserName;

    public BrowserInfo(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserInfo that = (BrowserInfo) o;

        return browserName != null ? browserName.equals(that.browserName) : that.browserName == null;
    }

    @Override
    public int hashCode() {
        return browserName != null ? browserName.hashCode() : 0;
    }
}
