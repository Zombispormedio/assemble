package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Recipient extends People {

    public int id;

    public Recipient( int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url) {
        super(email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
        this.id = id;
    }

    public Recipient() {
        this.id = 0;
    }
}
