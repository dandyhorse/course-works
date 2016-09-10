package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rinat
 */
@Entity
@Table(name = "Sites")
@EqualsAndHashCode
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(targetEntity = PageEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Pages",
            joinColumns = @JoinColumn(referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "SiteID"))
    private Set<PageEntity> pages;

    public SiteEntity() {
        pages = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PageEntity> getPages() {
        return pages;
    }

    public void setPages(Set<PageEntity> pages) {
        this.pages = pages;
    }

}
