package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Rinat
 * @since 9/3/2016
 */
@EqualsAndHashCode
public class PageStatistic {

    private String url;
    private String lastScanDate;
    private Integer rank;

    public PageStatistic(String url, LocalDate lastScanDate, Integer rank) {
        this.url = url;
        this.lastScanDate = lastScanDate.format(DateTimeFormatter.ISO_DATE);
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public String getLastScanDate() {
        return lastScanDate;
    }

    public Integer getRank() {
        return rank;
    }
}
