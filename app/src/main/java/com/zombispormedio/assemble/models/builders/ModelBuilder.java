package com.zombispormedio.assemble.models.builders;

import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.services.api.APIAuthService;


/**
 * Created by Master on 10/07/2016.
 */
public class ModelBuilder {

    public static UserResources createUser(){
        return new UserResources(null, null);
    }
}
