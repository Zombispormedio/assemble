package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Avatar {

    public final String full_avatar_url;

    public final String large_avatar_url;

    public final String medium_avatar_url;

    public final String thumb_avatar_url;

    public Avatar(String full_avatar_url, String large_avatar_url, String medium_avatar_url, String thumb_avatar_url) {
        this.full_avatar_url = full_avatar_url;
        this.large_avatar_url = large_avatar_url;
        this.medium_avatar_url = medium_avatar_url;
        this.thumb_avatar_url = thumb_avatar_url;
    }
}
