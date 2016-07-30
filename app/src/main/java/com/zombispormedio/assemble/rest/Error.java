package com.zombispormedio.assemble.rest;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class Error {
    public final String msg;
    public final String[] password;
    public final String[] email;

    public Error(String msg, String[] password, String[] email) {
        this.msg = msg;
        this.password = password;
        this.email = email;
    }

    public Error(String msg) {
        this.msg = msg;
        this.password = null;
        this.email = null;
    }
}
