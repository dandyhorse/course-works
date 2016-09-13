package org.lenteam.colmen.models;

import lombok.EqualsAndHashCode;

/**
 * @author Anton_Solovev
 * @author Rinat
 * @since 9/1/2016
 */
@EqualsAndHashCode
public class DailyStatistic {

    private String personName;
    private String siteName;
    private Iterable<PageStatistic> pagesByDays;
    private Long totalPages;

    public DailyStatistic(String personName, String siteName, Iterable<PageStatistic> pagesByDays, Long totalPages) {
        this.personName = personName;
        this.siteName = siteName;
        this.pagesByDays = pagesByDays;
        this.totalPages = totalPages;
    }

    public String getPersonName() {
        return personName;
    }

    public String getSiteName() {
        return siteName;
    }

    public Iterable<PageStatistic> getPagesByDays() {
        return pagesByDays;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}

