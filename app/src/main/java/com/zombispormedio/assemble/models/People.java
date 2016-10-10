package com.zombispormedio.assemble.models;


/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class People extends BaseModel{

    public String email;

    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;

    public People(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url) {
        super(id);
        this.email = email;
        this.username = username;
        this.full_avatar_url = full_avatar_url;
        this.large_avatar_url = large_avatar_url;
        this.medium_avatar_url = medium_avatar_url;
        this.thumb_avatar_url = thumb_avatar_url;
    }

    public People() {
        super(0);
        this.email = "";
        this.username = "";
    }
}
