package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Profile {

    public final String email;

    public final String username;
    public final String birth_date;

    public final String location;
    public final String bio;

    public final String sign_up_at;
    public final String full_avatar_url;
    public final String large_avatar_url;
    public final String medium_avatar_url;
    public final String thumb_avatar_url;

    public Profile(String email, String username, String birth_date, String location, String bio, String sign_up_at, String full_avatar_url, String large_avatar_url, String medium_avatar_url, String thumb_avatar_url) {
        this.email = email;
        this.username = username;
        this.birth_date = birth_date;
        this.location = location;
        this.bio = bio;
        this.sign_up_at = sign_up_at;
        this.full_avatar_url = full_avatar_url;
        this.large_avatar_url = large_avatar_url;
        this.medium_avatar_url = medium_avatar_url;
        this.thumb_avatar_url = thumb_avatar_url;
    }

    public Profile(){

        email = "";
        username="";
        birth_date="";
        location="";
        bio ="";
        sign_up_at="";
        full_avatar_url="";
        large_avatar_url="";
        medium_avatar_url="";
        thumb_avatar_url="";


    }

}
