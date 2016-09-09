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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonPageRankEntity that = (PersonPageRankEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (person != null ? !person.equals(that.person) : that.person != null) return false;
        return page != null ? page.equals(that.page) : that.page == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        return result;
    }
}
