package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.ILoginView;

/**
 * Created by Xavier Serrano on 09/07/2016.
 */
public class LoginController extends Controller {

    private ILoginView ctx;

    private UserResource user;

    public LoginController(ILoginView ctx) {
        super(ctx);
        this.ctx = ctx;
        user = getResourceComponent().provideUserResource();
    }

    public void login() {

        if (!ctx.validate()) {
            ctx.showFailValidation();

        } else {
            String email = ctx.getEmail();
            String pass = ctx.getPassword();

            whileLogin();

            user.login(email, pass, new LoginServiceHandler());
        }
    }

    public class LoginServiceHandler extends ServiceHandler<Result, Error> {

        @Override
        public void onError(Error error) {
            ctx.showAlert(error.msg);
            afterTryLogin();
        }

        @Override
        public void onSuccess(Result result) {
            ctx.setAuthToken(result.token);

            ctx.showSuccessfulLogin();

            ctx.goHome();
        }

        @Override
        public void onNotConnected() {
            ctx.showAlert("No internet connection");
        }
    }

    private void whileLogin() {
        ctx.hideForm();
        ctx.showProgressBar();
    }

    private void afterTryLogin() {
        ctx.hideProgressBar();
        ctx.showForm();
    }

    public void linkToRegister() {
        ctx.goToRegister();
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }
}
