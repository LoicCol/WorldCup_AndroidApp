package com.world.cup.classes;

/**
 * Created by loiccol on 02/04/18.
 */

public class Team {
    private int id;
    private String name;
    private String group;
    private String logoCode;
    private int points;

    public Team(int id, String name, String group, String logoCode, int points) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.logoCode = logoCode;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getLogoCode() {
        return logoCode;
    }

    public int getPoints() {
        return points;
    }
}
