package com.zombispormedio.assemble.rest;

import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.rest.responses.DefaultResponse;
import com.zombispormedio.assemble.rest.responses.ProfileResponse;
import com.zombispormedio.assemble.wrappers.moshi.JSONWrapper;

import java.io.IOException;

/**
 * Created by Master on 26/07/2016.
 */
public class JSONBinder {

    public static DefaultResponse toDefaultResponse(String raw) throws IOException {
        JSONWrapper<DefaultResponse> jsonAdapter=new JSONWrapper<DefaultResponse>(DefaultResponse.class);
        return  jsonAdapter.fromJSON(raw);
    }

    public static String fromUser(User user){
        JSONWrapper<User> userAdapter=new JSONWrapper<User>(User.class);
        return userAdapter.toJSON(user);
    }

    public static ProfileResponse toProfileResponse(String raw) throws IOException {
        JSONWrapper<ProfileResponse> jsonAdapter=new JSONWrapper<ProfileResponse>(ProfileResponse.class);
        return  jsonAdapter.fromJSON(raw);
    }



}
