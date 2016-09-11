package org.lenteam.colmen.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rinat
 */
@Entity
@Table(name = "Sites")
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<PageEntity> pages;

    public SiteEntity() {
        pages = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteEntity that = (SiteEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
