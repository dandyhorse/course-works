package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rinat
 */
@Entity
@Table(name = "pages")
@EqualsAndHashCode
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "found_date_time")
    private Date foundDateTime;

    @Column(name = "last_scan_date")
    private Date lastScanDate;

    @ManyToOne(targetEntity = SiteEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id")
    private SiteEntity site;

    @OneToMany(targetEntity = PersonPageRankEntity.class, fetch = FetchType.EAGER)
    private Set<PersonPageRankEntity> ranks;

    public PageEntity() {
        ranks = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
