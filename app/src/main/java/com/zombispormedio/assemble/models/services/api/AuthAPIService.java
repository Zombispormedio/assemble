package com.zombispormedio.assemble.models.services.api;


import com.zombispormedio.assemble.handlers.IServiceHandler;

import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.models.services.interfaces.IAuthService;


/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public class AuthAPIService implements IAuthService {

    private APIConfiguration api;

    public AuthAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void checkAccess(final IServiceHandler<Result, Error> handler) {

        api.RestWithAuth("/check_user")
                .handler(DeferUtils.defer(handler))
                .get();

    }

    @Override
    public void login(String email, String password, final IServiceHandler<Result, Error> handler) {
        Auth user = new Auth(email, password);
        api.Rest("/login")
                .handler(DeferUtils.defer(handler))
                .post(JsonBinder.fromAuth(user));
    }

    @Override
    public void register(String email, String password, final IServiceHandler<Result, Error> handler) {
        Auth user = new Auth(email, password);
        api.Rest("/signup")
                .handler(DeferUtils.defer(handler))
                .post(JsonBinder.fromAuth(user));
    }

    @Override
    public void signOut(final IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/signout")
                .handler(DeferUtils.defer(handler))
                .get();
    }






}
