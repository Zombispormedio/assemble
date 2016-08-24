package com.zombispormedio.assemble.rest;

import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.rest.responses.DefaultResponse;
import com.zombispormedio.assemble.rest.responses.ProfileResponse;
import com.zombispormedio.assemble.wrappers.moshi.JSONWrapper;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class JsonBinder {

    public static DefaultResponse toDefaultResponse(String raw) throws IOException {
        JSONWrapper<DefaultResponse> jsonAdapter=new JSONWrapper<>(DefaultResponse.class);
        return  jsonAdapter.fromJSON(raw);
    }

    public static String fromUserProfile(UserProfile userProfile){
        JSONWrapper<UserProfile> userAdapter= new JSONWrapper<>(UserProfile.class);
        return userAdapter.toJSON(userProfile);
    }

    public static ProfileResponse toProfileResponse(String raw) throws IOException {
        JSONWrapper<ProfileResponse> jsonAdapter= new JSONWrapper<>(ProfileResponse.class);
        return  jsonAdapter.fromJSON(raw);
    }


    public static String fromAuth(Auth user) {
        JSONWrapper<Auth> userAdapter= new JSONWrapper<>(Auth.class);
        return userAdapter.toJSON(user);
    }

    public static String fromEditProfile(EditProfile profile) {
        JSONWrapper<EditProfile> editProfileAdapter= new JSONWrapper<>(EditProfile.class);
        return editProfileAdapter.toJSON(profile);
    }
}
