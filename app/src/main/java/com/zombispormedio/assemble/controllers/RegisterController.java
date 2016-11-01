package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.activities.IRegisterView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 09/07/2016.
 */
public class RegisterController extends Controller {

    @Nullable
    private IRegisterView ctx;

    private final UserResource user;

    public RegisterController(IRegisterView ctx) {
        super(ctx);
        this.ctx = ctx;
        user = getResourceComponent().provideUserResource();
    }

    public void register() {
        whileLogin();

        String email = ctx.getEmail();
        String pass = ctx.getPassword();
        String rep_pass = ctx.getRepPassword();

        if (email.isEmpty() || pass.isEmpty() || rep_pass.isEmpty()) {
            afterTryLogin();
            if (!email.isEmpty()) {
                if (pass.isEmpty()) {
                    ctx.showEmptyPassword();
                } else if (rep_pass.isEmpty()) {
                    ctx.showEmptyRepPassword();
                } else {
                    ctx.showEmptyEmail();
                }
            }
        } else if (!pass.equals(rep_pass)) {
            afterTryLogin();
            ctx.showNotEqualsBothPassword();

        } else {

            Auth auth = new Auth(email, pass);

            user.signin(auth, new RegisterServiceHandler());
        }
    }

    private class RegisterServiceHandler extends ServiceHandler<Result, Error> {

        @Override
        public void onError(@NonNull Error error) {
            String alert = "";
            boolean alerted = false;

            if (error.email != null) {
                if (error.email.length > 0) {
                    alert = error.email[0];
                    alerted = true;
                } else if (error.password != null) {
                    if (error.password.length > 0) {
                        alert = error.password[0];
                        alerted = true;
                    } else if (error.msg != null) {
                        alert = error.msg;
                        alerted = true;
                    }
                }
            }

            if (!alerted) {
                ctx.showUnknowError();
            } else {
                ctx.showAlert(alert);
            }

            afterTryLogin();
        }

        @Override
        public void onSuccess(Result result) {
            ctx.showSuccessfulRegister();
            ctx.goToLogin();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }

}
