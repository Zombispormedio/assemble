package com.zombispormedio.assemble.services.api;

/**
 * Created by Master on 26/07/2016.
 */
public class APIConfiguration {
    private static APIConfiguration ourInstance = new APIConfiguration();

    public static APIConfiguration getInstance() {
        return ourInstance;
    }

    private APIConfiguration() {
    }
}
