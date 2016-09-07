package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Profile extends Avatar{

    public final String email;

    public final String username;

    public final String birth_date;

    public final String location;

    public final String bio;

    public final String sign_up_at;



    public Profile(String email, String username, String birth_date, String location, String bio, String sign_up_at,
            String full_avatar_url, String large_avatar_url, String medium_avatar_url, String thumb_avatar_url) {
        super(full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
        this.email = email;
        this.username = username;
        this.birth_date = birth_date;
        this.location = location;
        this.bio = bio;
        this.sign_up_at = sign_up_at;
    }

    public Profile() {
        super("", "", "", "");
        email = "";
        username = "";
        birth_date = "";
        location = "";
        bio = "";
        sign_up_at = "";

    }

}
