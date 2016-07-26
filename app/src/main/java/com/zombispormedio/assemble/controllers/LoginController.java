package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.views.ILoginView;

/**
 * Created by Master on 09/07/2016.
 */
public class LoginController implements IBaseController {

    private ILoginView ctx;
    private UserResource user;

    public LoginController(ILoginView ctx) {
        this.ctx = ctx;

        user= ResourceFactory.createUser();


    }

    public void onClickLoginButton() {
        whileLogin();

        String email=ctx.getEmail();
        String pass=ctx.getPassword();

        if(email.isEmpty() || pass.isEmpty()){
            afterTryLogin();
            if(email.isEmpty()){
                ctx.showEmptyEmail();
            }else{
                if(pass.isEmpty()){
                    ctx.showEmptyPassword();
                }
            }

        }else{
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

    private void whileLogin(){
        ctx.hideForm();
        ctx.showProgressBar();
    }

    private void afterTryLogin(){
        ctx.hideProgressBar();
        ctx.showForm();
    }

    public void onClickRegisterLink() {
        ctx.goToRegister();
    }



    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
