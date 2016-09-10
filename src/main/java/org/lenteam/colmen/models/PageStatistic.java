package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author Rinat
 * @since 9/3/2016
 */
@EqualsAndHashCode
public class PageStatistic {

    private String url;
    private LocalDate lastScanDate;
    private Integer pageCount;

    public PageStatistic(String url, LocalDate lastScanDate, Integer rank) {
        this.url = url;
        this.lastScanDate = lastScanDate;
        this.pageCount = rank;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getLastScanDate() {
        return lastScanDate;
    }

    public Integer getPageCount() {
        return pageCount;
    }
}
