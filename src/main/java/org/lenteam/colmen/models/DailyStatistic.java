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
    private Iterable<PageStatistic> pagesOnSite;

    public DailyStatistic(String personName, String siteName, Iterable<PageStatistic> pagesOnSite) {
        this.personName = personName;
        this.siteName = siteName;
        this.pagesOnSite = pagesOnSite;
    }

    public String getPersonName() {
        return personName;
    }

    public String getSiteName() {
        return siteName;
    }

    public Iterable<PageStatistic> getPagesOnSite() {
        return pagesOnSite;
    }
}

