package com.zombispormedio.assemble.models.resources;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.models.services.interfaces.IAuthService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import javax.inject.Inject;


/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class UserResource {

    private IAuthService service;

    @Inject
    public UserResource(IAuthService service) {
        this.service = service;
    }

    public void checkAccess(IServiceHandler<Result, Error> listener) {
        service.checkAccess(listener);
    }


    public void login(Auth auth, IServiceHandler<Result, Error> listener) {
        service.login(auth, listener);
    }


    public void signin(Auth auth, IServiceHandler<Result, Error> listener) {
        service.register(auth, listener);
    }


    public void signOut(final IServiceHandler<Result, Error> handler) {
        service.signOut(new ServiceHandler<Result, Error>() {
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Result result) {
                LocalStorage.Configuration.deleteAll();
                handler.onSuccess(result);
            }

        });

    }

    public void refreshGCM(String gcmToken, final IServiceHandler<Result, Error> handler){
        service.refreshGCM(gcmToken, handler);
    }



}
