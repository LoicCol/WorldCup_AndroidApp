package com.world.cup.entities;

/**
 * Created by loiccol on 04/05/18.
 */

public class Forecast {
    private int gameId;
    private int scoreTeam1;
    private int scoreTeam2;
    private int homeTeamId;
    private int awayTeamId;

    public Forecast(int gameId, int scoreTeam1, int scoreTeam2, int homeTeamId, int awayTeamId) {
        this.gameId = gameId;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }
}
