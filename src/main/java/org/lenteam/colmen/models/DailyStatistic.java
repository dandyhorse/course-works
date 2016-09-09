package org.lenteam.colmen.models;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public class DailyStatistic {

    private String personName;
    private String siteName;
    private Long personRank;
    private Iterable<Page> pagesOnSite;

    public DailyStatistic(String personName, String siteName, Long personRank, Iterable<Page> pagesOnSite) {
        this.personName = personName;
        this.siteName = siteName;
        this.personRank = personRank;
        this.pagesOnSite = pagesOnSite;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getPersonRank() {
        return personRank;
    }

    public void setPersonRank(Long personRank) {
        this.personRank = personRank;
    }

    public Iterable<Page> getPagesOnSite() {
        return pagesOnSite;
    }

    public void setPagesOnSite(Iterable<Page> pagesOnSite) {
        this.pagesOnSite = pagesOnSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyStatistic that = (DailyStatistic) o;

        if (personName != null ? !personName.equals(that.personName) : that.personName != null) return false;
        if (siteName != null ? !siteName.equals(that.siteName) : that.siteName != null) return false;
        if (personRank != null ? !personRank.equals(that.personRank) : that.personRank != null) return false;
        return pagesOnSite != null ? pagesOnSite.equals(that.pagesOnSite) : that.pagesOnSite == null;

    }

    @Override
    public int hashCode() {
        int result = personName != null ? personName.hashCode() : 0;
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (personRank != null ? personRank.hashCode() : 0);
        result = 31 * result + (pagesOnSite != null ? pagesOnSite.hashCode() : 0);
        return result;
    }
}

