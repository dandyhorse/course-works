package org.lenteam.colmen.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "foundDateTime")
    private String foundDateTime;

    @Column(name = "lastScanDate")
    private String lastScanDate;

    @ManyToOne
    @JoinColumn(name = "sites_id")
    private SiteEntity site;

    @OneToMany(mappedBy = "page")
    private Set<PersonPageRankEntity> ranks = new HashSet<>();

    public PageEntity() {
        //no args constructor
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

    public String getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(String foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public String getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(String lastScanDate) {
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
