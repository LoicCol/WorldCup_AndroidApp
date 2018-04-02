package com.world.cup.classes;

/**
 * Created by loiccol on 02/04/18.
 */

public class User {
    private String pseudo;
    private String name;
    private String email;
    private int id;

    public User(String pseudo, String name, String email, int id) {
        this.pseudo = pseudo;
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
