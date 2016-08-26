package com.zombispormedio.assemble.rest;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class Result {

    public final String token;

    public final String msg;

    public final String full_avatar_url;

    public final String large_avatar_url;

    public final String medium_avatar_url;

    public final String thumb_avatar_url;

    public Result(String token, String msg, String full_avatar_url, String large_avatar_url, String medium_avatar_url,
            String thumb_avatar_url) {
        this.token = token;
        this.msg = msg;
        this.full_avatar_url = full_avatar_url;
        this.large_avatar_url = large_avatar_url;
        this.medium_avatar_url = medium_avatar_url;
        this.thumb_avatar_url = thumb_avatar_url;
    }
}
