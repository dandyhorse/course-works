package org.lenteam.colmen.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 */
@Entity
@Table(name = "Persons")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "person")
    private Set<KeywordEntity> keywords = new HashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<PersonPageRank> ranks = new HashSet<>();

    public PersonEntity() {
        //no args constructor
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

    public Set<PersonPageRank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<PersonPageRank> ranks) {
        this.ranks = ranks;
    }
}
