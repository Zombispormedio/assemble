package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler2;

import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.views.ILoginView;

/**
 * Created by Master on 09/07/2016.
 */
public class LoginController implements IBaseController {

    private ILoginView ctx;
    private UserResources user;

    public LoginController(ILoginView ctx) {
        this.ctx = ctx;

        user= ModelBuilder.createUser();


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

    public class LoginServiceHandler implements IServiceHandler2<String, String> {

        @Override
        public void onError(String... args) {
            String error=args[0];

            ctx.showAlert(error);
            afterTryLogin();

        }

        @Override
        public void onSuccess(String... args) {

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
