package com.zombispormedio.assemble.services.api;


import com.zombispormedio.assemble.handlers.IServiceHandler2;

import com.zombispormedio.assemble.services.IAuthService;


/**
 * Created by Master on 25/07/2016.
 */
public class APIAuthService implements IAuthService {



    public APIAuthService() {

    }

    @Override
    public void checkAccess(IServiceHandler2 handler) {
    }



    @Override
    public void login(String email, String password, IServiceHandler2<String, String> listener) {

    }

    @Override
    public void create(String email, String password, IServiceHandler2<String, String> listener) {

    }

    @Override
    public void signOut() {

    }

    @Override
    public String getValue(String field) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }
}
