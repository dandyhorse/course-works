package org.lenteam.colmen.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Rinat
 */
@Entity
@Table(name = "PersonPageRank")
@EqualsAndHashCode
public class PersonPageRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Rank")
    private Integer rank;

    @ManyToOne(targetEntity = PersonEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PersonID")
    private PersonEntity person;

    @ManyToOne(targetEntity = PageEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PageID")
    private PageEntity page;

    public PersonPageRankEntity() {
        //no args constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

}
