package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Rinat
 */
@Entity
@Table(name = "keywords")
@EqualsAndHashCode
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = PersonEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public KeywordEntity() {
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

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

}
