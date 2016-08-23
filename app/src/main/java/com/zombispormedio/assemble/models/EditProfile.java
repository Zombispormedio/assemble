package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class EditProfile {

    public final String username;
    public final String bio;
    public final String location;
    public final String birth_date;

    public EditProfile(String username, String bio, String location, String birth_date) {
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.birth_date = birth_date;
    }

    public static class Builder{

    }
}
