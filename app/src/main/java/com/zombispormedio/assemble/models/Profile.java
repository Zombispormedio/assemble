package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Profile extends People {

    public final String birth_date;

    public final String location;

    public final String bio;

    public final String sign_up_at;

    public Profile(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url, String birth_date, String location, String bio,
            String sign_up_at) {
        super(id, email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
        this.birth_date = birth_date;
        this.location = location;
        this.bio = bio;
        this.sign_up_at = sign_up_at;
    }

}
