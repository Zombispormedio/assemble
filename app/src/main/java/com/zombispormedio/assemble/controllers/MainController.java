package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.services.api.APIConfiguration;
import com.zombispormedio.assemble.views.IMainView;

/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class MainController extends AbstractController {

    private IMainView ctx;

    private UserResource user;

    public MainController(IMainView ctx) {
        this.ctx = ctx;
        user = ResourceFactory.createUser();
    }

    public void checkAccess() {
        String token = ctx.getAuthToken();
        if (token.isEmpty()) {
            ctx.goToLogin();
        } else {

            APIConfiguration.getInstance().setToken(token);

            user.checkAccess(new IServiceHandler<Result, Error>() {
                @Override
                public void onError(Error error) {

                    ctx.clearAuthToken();
                    ctx.goToLogin();
                }

                @Override
                public void onSuccess(Result result) {
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
