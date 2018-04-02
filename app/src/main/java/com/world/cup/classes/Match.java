package com.world.cup.classes;

import java.sql.Date;

/**
 * Created by loiccol on 02/04/18.
 */

public class Match {
    private int id;
    private Date dateTime;
    private String type;
    private String group;
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;
    private Stadium stadium;

    public Match(int id, Date dateTime, String type, String group, Team team1, Team team2, int score1, int score2, Stadium stadium) {
        this.id = id;
        this.dateTime = dateTime;
        this.type = type;
        this.group = group;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.stadium = stadium;
    }

    public Match(int id, Date dateTime, String type, String group, Team team1, Team team2, Stadium stadium) {
        this.id = id;
        this.dateTime = dateTime;
        this.type = type;
        this.group = group;
        this.team1 = team1;
        this.team2 = team2;
        this.stadium = stadium;
    }

    public int getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
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

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
