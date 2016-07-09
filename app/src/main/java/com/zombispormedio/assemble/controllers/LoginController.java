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
        
    }

    public void onClickRegisterLink() {
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
