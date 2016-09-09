package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.net.Request;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class APIConfiguration {

    private static APIConfiguration ourInstance = new APIConfiguration();

    private String baseUrl;

    private String token;

    public static APIConfiguration getInstance() {
        return ourInstance;
    }

    private APIConfiguration() {
        baseUrl = "https://us-assemble-api.herokuapp.com";
        token = null;
    }

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

    public Request.Builder Rest(String path) {
        return new Request.Builder()
                .url(getPath(path));
    }

    public Request.Builder RestWithAuth(String path) {
        return Rest(path)
                .headers("Authorization", token);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
