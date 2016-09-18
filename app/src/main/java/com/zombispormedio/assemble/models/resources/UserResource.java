package com.zombispormedio.assemble.models.resources;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.services.interfaces.IAuthService;
import com.zombispormedio.assemble.services.interfaces.IProfileService;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;


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
        CurrentUser.reset();
    }



}
