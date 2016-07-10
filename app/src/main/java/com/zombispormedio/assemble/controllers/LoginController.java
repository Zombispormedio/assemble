package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.views.ILoginView;

/**
 * Created by Master on 09/07/2016.
 */
public class LoginController implements IBaseController {

    private ILoginView ctx;
    private User user;
    private User.AccessVerifier verifier;

    public LoginController(ILoginView ctx) {
        this.ctx = ctx;

        user=new User();
        verifier=user.createAccessVerifier(new AccessListener());

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
            user.login(email, pass, new LoginListener());

        }


    }

    public class LoginListener implements IBaseListener<String>{

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


    public class AccessListener implements IBaseListener<Integer>{

        @Override
        public void onError(Integer... args) {

        }

        @Override
        public void onSuccess(Integer... args) {
            ctx.goHome();
        }
    }


    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {
        verifier.start();
    }

    @Override
    public void onStop() {
        verifier.stop();
    }
}
