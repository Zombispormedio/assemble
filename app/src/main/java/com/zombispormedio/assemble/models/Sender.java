package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Sender extends Avatar {

    public final String id;
    public final String email;
    public final String username;

    public Sender(String id,String email, String username,
            String full_avatar_url, String large_avatar_url, String medium_avatar_url, String thumb_avatar_url) {
        super(full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
