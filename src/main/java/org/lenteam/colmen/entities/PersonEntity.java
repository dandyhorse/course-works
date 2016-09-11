package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton_Solovev
 * @author Rinat
 * @since 8/21/2016
 */

@Entity
@Table(name = "Persons")
public class PersonEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<KeywordEntity> keywords;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<PersonPageRankEntity> ranks;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String name) {
        this.id = id;
        this.name = name;
        keywords = new HashSet<>();
        ranks = new HashSet<>();
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

    public Set<KeywordEntity> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<KeywordEntity> keywords) {
        this.keywords = keywords;
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

        PersonEntity that = (PersonEntity) o;

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
