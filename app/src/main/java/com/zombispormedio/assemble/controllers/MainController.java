package com.zombispormedio.assemble.controllers;


import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.activities.IMainView;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class MainController extends Controller {

    private IMainView ctx;

    private final UserResource user;

    public MainController(IMainView ctx) {
        super(ctx);
        this.ctx = ctx;
        user = getResourceComponent().provideUserResource();
    }

    public void checkAccess() {
        String token = ctx.getAuthToken();
        if (token.isEmpty()) {
            ctx.goToLogin();
        } else {

            user.checkAccess(new ServiceHandler<Result, Error>() {
                @Override
                public void onError(Error error){
                    ctx.clearAuthToken();
                    try {
                        FirebaseInstanceId.getInstance().deleteInstanceId();
                    } catch (IOException e) {
                        Logger.d(e.getMessage());
                        FirebaseCrash.report(e);
                    }
                    ctx.goToLogin();
                }

                @Override
                public void onSuccess(Result result) {
                    ctx.goHome();
                }

                @Override
                public void onNotConnected() {
                    ctx.goHome();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }

}
