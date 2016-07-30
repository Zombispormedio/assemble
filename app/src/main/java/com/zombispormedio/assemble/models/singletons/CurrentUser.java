package com.zombispormedio.assemble.models.singletons;

import com.zombispormedio.assemble.models.UserProfile;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class CurrentUser {
    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private UserProfile profile;

    private CurrentUser() {
        profile=null;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
