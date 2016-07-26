package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.services.api.APIAuthService;


/**
 * Created by Master on 10/07/2016.
 */
public class ResourceFactory {

    public static UserResources createUser(){
        return new UserResources(new APIAuthService(), null);
    }
}
