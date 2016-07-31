package com.epam.memorina.models;


/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */
public class GameStatistic {

    private Integer gamesFinishedCount;
    private Long timeInGame;

    /**
     * @param gamesFinishedCount count of games that the gamer had played
     * @param timeInGame         given in seconds
     */
    public GameStatistic(Integer gamesFinishedCount, Long timeInGame) {
        this.gamesFinishedCount = gamesFinishedCount;
        this.timeInGame = timeInGame;
    }

    public GameStatistic() {
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
