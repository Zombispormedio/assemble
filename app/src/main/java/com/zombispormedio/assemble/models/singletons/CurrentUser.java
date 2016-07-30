package com.zombispormedio.assemble.models.singletons;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class CurrentUser {
    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
    }
}
