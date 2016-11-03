package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.network.Request;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class APIConfiguration {

    private static final APIConfiguration ourInstance = new APIConfiguration();

    @NonNull
    private final String baseUrl;


    private String token;

    @NonNull
    public static APIConfiguration getInstance() {
        return ourInstance;
    }

    private APIConfiguration() {
        baseUrl = "https://us-assemble-api.herokuapp.com";
        token = null;
    }

    @NonNull
    public String getPath(String path) {
        return baseUrl + path;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean haveToken() {
        boolean have = token != null;
        if (have) {
            have = token.isEmpty();
        }
        return have;
    }

    public void clearToken() {
        token = null;
    }

    @NonNull
    public Request.Builder Rest(String path) {
        return new Request.Builder()
                .url(getPath(path));
    }

    @NonNull
    public Request.Builder RestWithAuth(String path) {
        return Rest(path)
                .headers("Authorization", token);
    }

    @NonNull
    public String getBaseUrl() {
        return baseUrl;
    }
}
