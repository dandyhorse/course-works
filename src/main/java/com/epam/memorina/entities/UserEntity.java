package com.epam.memorina.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * @author Solovev Anton
 * @since 26.07.2016.
 */
@Entity
@Table(name = "users")
public class UserEntity extends AbstractPersistable<Long> {

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    private String username;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;
    @PrimaryKeyJoinColumn
    @JoinTable(name = "games_statistic")
    @OneToOne(targetEntity = GameStatisticEntity.class, cascade = CascadeType.ALL)
    private GameStatisticEntity gameStatistic;

    public UserEntity() {
        super();
    }

    public UserEntity(String username, String password, GameStatisticEntity gameStatistic) {
        super();
        this.username = username;
        this.password = password;
        this.gameStatistic = gameStatistic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GameStatisticEntity getGameStatistic() {
        return gameStatistic;
    }

    public void setGameStatistic(GameStatisticEntity gameStatistic) {
        this.gameStatistic = gameStatistic;
    }

}
