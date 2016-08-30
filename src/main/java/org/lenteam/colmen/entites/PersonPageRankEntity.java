package org.lenteam.colmen.entites;

import javax.persistence.*;

@Entity
@Table(name = "PersonPageRankEntity")
public class PersonPageRankEntity {

    @Column(name = "rank")
    private Long rank;

    @ManyToOne
    @JoinColumn(name = "persons_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "pages_id")
    private PageEntity page;

    public PersonPageRankEntity() {
        //no args constructor
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
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
