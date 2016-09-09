package org.lenteam.colmen.models;

import java.sql.Date;

public class PageStatistic {

    private String url;
    private Date lastScanDate;
    private Integer personRank;

    public PageStatistic(String url, Date lastScanDate, Integer personRank) {
        this.url = url;
        this.lastScanDate = lastScanDate;
        this.personRank = personRank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public Integer getPersonRank() {
        return personRank;
    }

    public void setPersonRank(Integer personRank) {
        this.personRank = personRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageStatistic that = (PageStatistic) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (lastScanDate != null ? !lastScanDate.equals(that.lastScanDate) : that.lastScanDate != null) return false;
        return personRank != null ? personRank.equals(that.personRank) : that.personRank == null;

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (lastScanDate != null ? lastScanDate.hashCode() : 0);
        result = 31 * result + (personRank != null ? personRank.hashCode() : 0);
        return result;
    }
}
