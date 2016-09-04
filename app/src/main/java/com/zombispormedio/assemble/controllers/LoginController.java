package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler;

import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.ILoginView;

/**
 * Created by Xavier Serrano on 09/07/2016.
 */
public class LoginController extends AbstractController {

    private ILoginView ctx;

    private UserResource user;

    public LoginController(ILoginView ctx) {
        this.ctx = ctx;
        user = ResourceFactory.createUserResource();
    }

    public void onClickLoginButton() {

        if (!ctx.validate()) {
            ctx.showFailValidation();

        } else {
            String email = ctx.getEmail();
            String pass = ctx.getPassword();

            whileLogin();

            user.login(email, pass, new LoginServiceHandler());
        }
    }

    public class LoginServiceHandler implements IServiceHandler<Result, Error> {

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
    }

    private void whileLogin() {
        ctx.hideForm();
        ctx.showProgressBar();
    }

    private void afterTryLogin() {
        ctx.hideProgressBar();
        ctx.showForm();
    }

    public void onClickRegisterLink() {
        ctx.goToRegister();
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }
}
