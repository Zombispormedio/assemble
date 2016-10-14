package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Auth {

    public final String email;

    public final String password;

    public String gcm_token;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
