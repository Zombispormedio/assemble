package com.zombispormedio.assemble.models;


/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public class UserProfile extends Profile{

    public int id;

    public UserProfile(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url, String birth_date, String location, String bio,
            String sign_up_at) {
        super(email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url, birth_date, location,
                bio,
                sign_up_at);
        this.id = id;
    }

    public UserProfile() {
        this.id = 0;
    }
}
