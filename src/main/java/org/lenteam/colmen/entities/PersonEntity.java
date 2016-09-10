package org.lenteam.colmen.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@Table(name = "persons")
@EqualsAndHashCode
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = KeywordEntity.class, fetch = FetchType.EAGER)
    private Set<KeywordEntity> keywords;

    @OneToMany(targetEntity = PersonPageRankEntity.class, fetch = FetchType.EAGER)
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

}
