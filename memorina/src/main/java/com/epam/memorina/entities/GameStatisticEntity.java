package com.epam.memorina.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
@Entity
@Table(name = "games_statistic")
public class GameStatisticEntity extends AbstractPersistable<Long> {

    @Basic
    @Column
    private Integer gamesFinishedCount;
    @Basic
    @Column
    private Long timeInGame;

    public GameStatisticEntity(Integer gamesFinishedCount, Long timeInGame) {
        super();
        this.gamesFinishedCount = gamesFinishedCount;
        this.timeInGame = timeInGame;
    }

    public GameStatisticEntity() {
        super();
    }

    public Integer getGamesFinishedCount() {
        return gamesFinishedCount;
    }

    public void setGamesFinishedCount(Integer gamesFinishedCount) {
        this.gamesFinishedCount = gamesFinishedCount;
    }

    public Long getTimeInGame() {
        return timeInGame;
    }

    public void setTimeInGame(Long timeInGame) {
        this.timeInGame = timeInGame;
    }
}
