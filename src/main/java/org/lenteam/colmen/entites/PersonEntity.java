package org.lenteam.colmen.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 */
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person")
    private Set<KeywordEntity> keywords;

    @OneToMany(mappedBy = "person")
    private Set<PersonPageRankEntity> ranks;

    public PersonEntity() {
        keywords = new HashSet<>();
        ranks = new HashSet<>();
    }

    public PersonEntity(String name) {
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
}
