package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.models.services.api.APIConfiguration;
import com.zombispormedio.assemble.views.activities.IMainView;

/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class MainController extends Controller {

    private IMainView ctx;

    private UserResource user;

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

            APIConfiguration.getInstance().setToken(token);

            user.checkAccess(new ServiceHandler<Result, Error>() {
                @Override
                public void onError(Error error) {
                    ctx.clearAuthToken();
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
