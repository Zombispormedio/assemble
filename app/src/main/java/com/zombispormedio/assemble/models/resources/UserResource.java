package com.zombispormedio.assemble.models.resources;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.services.interfaces.IAuthService;

import javax.inject.Inject;

import io.realm.Realm;


/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class UserResource {

    private IAuthService auth;

    @Inject
    public UserResource(IAuthService auth) {
        this.auth = auth;
    }

    public void checkAccess(IServiceHandler<Result, Error> listener) {
        auth.checkAccess(listener);
    }


    public void login(String email, String password, IServiceHandler<Result, Error> listener) {
        auth.login(email, password, listener);
    }


    public void signin(String email, String password, IServiceHandler<Result, Error> listener) {
        auth.register(email, password, listener);
    }


    public void signOut(final IServiceHandler<Result, Error> handler) {
        auth.signOut(handler);
        Realm.getDefaultInstance().deleteAll();
    }



}
