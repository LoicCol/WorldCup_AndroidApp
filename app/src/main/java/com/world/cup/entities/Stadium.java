package com.world.cup.entities;

/**
 * Created by loiccol on 02/04/18.
 */

public class Stadium {
    private int id;
    private String name;
    private String city;
    private double lat;
    private double lon;

    public Stadium(int id, String name, double lat, double lon, String city) {
        this.setId(id);
        this.setName(name);
        this.setLat(lat);
        this.setLon(lon);
        this.setCity(city);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
