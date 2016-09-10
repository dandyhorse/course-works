package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Rinat
 */
@Entity
@Table(name = "Keywords")
@EqualsAndHashCode
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToOne(targetEntity = PersonEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "personID")
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
