package com.world.cup.entities;

/**
 * Created by loiccol on 02/04/18.
 */

public class Team {
    private int id;
    private String name;
    private String group;
    private String logoCode;
    private int points;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", logoCode='" + logoCode + '\'' +
                ", points=" + points +
                '}';
    }

    public Team(int id, String name, String group, String logoCode, int points) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.logoCode = logoCode;
        this.points = points;
    }

    public Team(String name) {
        this.name = name;
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
