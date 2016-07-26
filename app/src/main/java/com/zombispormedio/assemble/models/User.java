package com.zombispormedio.assemble.models;

/**
 * Created by Master on 25/07/2016.
 */
public class User {
    public final String email;
    public final String password;

    public final String id;
    public final String username;

    public final String name;
    public final String birth_date;

    public final String location;
    public final String bio;

    public User(String email, String password, String id, String username, String name, String birth_date, String location, String bio) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.username = username;
        this.name = name;
        this.birth_date = birth_date;
        this.location = location;
        this.bio = bio;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = null;
        this.username = null;
        this.name = null;
        this.birth_date = null;
        this.location = null;
        this.bio = null;
    }
}
