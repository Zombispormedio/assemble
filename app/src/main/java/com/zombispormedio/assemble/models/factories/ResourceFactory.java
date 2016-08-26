package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.services.api.AuthAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ResourceFactory {

    public static UserResource createUser() {
        return new UserResource(new AuthAPIService(), new ProfileAPIService());
    }
}
