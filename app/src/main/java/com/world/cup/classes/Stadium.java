package com.world.cup.classes;

/**
 * Created by loiccol on 02/04/18.
 */

public class Stadium {
    private int id;
    private String name;
    private double lat;
    private double lon;

    public Stadium(int id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
