package com.epam.memorina.models;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
public class User {

    private String username;
    private String password;
    private GameStatistic gameStatistic;

    public User(String username, String password, GameStatistic gameStatistic) {
        this.username = username;
        this.password = password;
        this.gameStatistic = gameStatistic;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GameStatistic getGameStatistic() {
        return gameStatistic;
    }

    public void setGameStatistic(GameStatistic gameStatistic) {
        this.gameStatistic = gameStatistic;
    }
}
