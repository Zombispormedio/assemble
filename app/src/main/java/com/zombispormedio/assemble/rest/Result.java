package com.zombispormedio.assemble.rest;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class Result {
    public final String token;

    public final String msg;

    public Result(String token, String msg) {
        this.token = token;
        this.msg = msg;
    }
}
