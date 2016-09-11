package org.lenteam.colmen.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rinat
 */
@Entity
@Table(name = "Pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")

    private Integer id;

    @Column(name = "Url")
    private String url;

    @Column(name = "FoundDateTime")
    private Date foundDateTime;

    @Column(name = "LastScanDate")
    private Date lastScanDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SiteID")
    private SiteEntity site;

    @OneToMany(mappedBy = "page", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<PersonPageRankEntity> ranks;

    public PageEntity() {
        ranks = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Date foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public Set<PersonPageRankEntity> getRanks() {
        return ranks;
    }

    public void setRanks(Set<PersonPageRankEntity> ranks) {
        this.ranks = ranks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageEntity that = (PageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (foundDateTime != null ? !foundDateTime.equals(that.foundDateTime) : that.foundDateTime != null)
            return false;
        if (lastScanDate != null ? !lastScanDate.equals(that.lastScanDate) : that.lastScanDate != null) return false;
        return site != null ? site.equals(that.site) : that.site == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (foundDateTime != null ? foundDateTime.hashCode() : 0);
        result = 31 * result + (lastScanDate != null ? lastScanDate.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }
}
