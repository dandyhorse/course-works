package org.lenteam.colmen.models;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
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

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Iterable<PageStatistic> getPagesOnSite() {
        return pagesOnSite;
    }

    public void setPagesOnSite(Iterable<PageStatistic> pagesOnSite) {
        this.pagesOnSite = pagesOnSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyStatistic that = (DailyStatistic) o;

        if (personName != null ? !personName.equals(that.personName) : that.personName != null) return false;
        if (siteName != null ? !siteName.equals(that.siteName) : that.siteName != null) return false;
        return pagesOnSite != null ? pagesOnSite.equals(that.pagesOnSite) : that.pagesOnSite == null;

    }

    @Override
    public int hashCode() {
        int result = personName != null ? personName.hashCode() : 0;
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (pagesOnSite != null ? pagesOnSite.hashCode() : 0);
        return result;
    }
}

