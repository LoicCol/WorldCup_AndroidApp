package com.world.cup.entities;

import android.util.Log;

/**
 * Created by loiccol on 02/04/18.
 */

public class Game {
    private static final String TAG = "Game";
    private int id;
    private String dateTime;
    private String type;
    private String group;
    private Team home_team;
    private Team away_team;
    private Integer score1;
    private Integer score2;
    private Stadium stadium;



    public Game(int id, String dateTime, String type, String group, Team team1, Team team2, Integer score1, Integer score2, Stadium stadium) {
        this.id = id;
        this.setDateTime(dateTime);
        this.type = type;
        this.group = group;
        this.home_team = team1;
        this.away_team = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.stadium = stadium;
    }

    public Game(int id, String dateTime, String type, String group, Team team1, Team team2, Stadium stadium) {
        this.id = id;
        this.setDateTime(dateTime);
        this.type = type;
        this.group = group;
        this.home_team = team1;
        this.away_team = team2;
        this.stadium = stadium;
    }

    public Game(String dateTime, String group, Team team1, Team team2, Integer score1, Integer score2) {
        this.setDateTime(dateTime);
        this.group = group;
        this.home_team = team1;
        this.away_team = team2;
        this.score1 = score1;
        this.score2 = score2;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", type='" + type + '\'' +
                ", group='" + group + '\'' +
                ", home_team=" + home_team.toString() +
                ", away_team=" + away_team.toString() +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", stadium=" + stadium +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    public Team getHomeTeam() {
        return home_team;
    }

    public Team getAwayTeam() {
        return away_team;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
