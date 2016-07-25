package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.wrappers.IRestWrapper;
import com.zombispormedio.assemble.wrappers.RetrofitWrapper;

/**
 * Created by Master on 25/07/2016.
 */
public class API {

    public static final String BASE_URL = "https://assemble-api.herokuapp.com/";

    private static final IRestWrapper rest = new RetrofitWrapper(API.BASE_URL);

    public static IRestWrapper getREST(){
        return rest;
    }

}
