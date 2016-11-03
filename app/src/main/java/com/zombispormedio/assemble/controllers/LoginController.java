package com.zombispormedio.assemble.controllers;

import com.onesignal.OneSignal;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;
import com.zombispormedio.assemble.views.activities.ILoginView;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 09/07/2016.
 */
public class LoginController extends Controller {


    private ILoginView ctx;

    private final UserResource user;

    @NonNull
    private final Auth.Builder editor;

    public LoginController(ILoginView ctx) {
        super(ctx);
        this.ctx = ctx;
        user = getResourceComponent().provideUserResource();
        editor = new Auth.Builder();
    }

    public void login() {

        if (!ctx.validate()) {
            ctx.showFailValidation();
        } else {
            whileLogin();

            editor.setEmail(ctx.getEmail())
                    .setPassword(ctx.getPassword());

            user.login(editor.build(), new LoginServiceHandler());
        }
    }

    private class LoginServiceHandler extends ServiceHandler<Result, Error> {

        @Override
        public void onError(@NonNull Error error) {
            ctx.showAlert(error.msg);
            afterTryLogin();
        }

        @Override
        public void onSuccess(@NonNull Result result) {
            configureAuthentication(result.token);
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

    private void configureAuthentication(String token) {
        ctx.setAuthToken(token);
        OneSignal.syncHashedEmail(editor.getEmail());
    }


    public void linkToRegister() {
        ctx.goToRegister();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
