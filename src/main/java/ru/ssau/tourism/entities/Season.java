package ru.ssau.tourism.entities;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate finishDate;
    @Column
    private Boolean isFinished;
    @Column
    private Integer countOfPlaces;

    public Long getId() {
        return id;
    }

    public Tour getTour() {
        return tour;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public Integer getCountOfPlaces() {
        return countOfPlaces;
    }
}
