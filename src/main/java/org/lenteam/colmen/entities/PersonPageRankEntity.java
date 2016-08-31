package org.lenteam.colmen.entities;

import javax.persistence.*;

@Entity
@Table(name = "person_page_rank")
public class PersonPageRankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rank")
    private Integer rank;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "page_id")
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
