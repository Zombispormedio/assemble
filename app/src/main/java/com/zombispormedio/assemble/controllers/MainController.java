package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.views.IMainView;

/**
 * Created by Master on 08/07/2016.
 */
public class MainController implements IBaseController {



    private IMainView ctx;
    private User user;
    private User.AccessVerifier verifier;
    public MainController(IMainView ctx) {
        this.ctx=ctx;
        user= ModelBuilder.createUser();
        verifier=user.createAccessVerifier(new AccessListener());
    }

    public class AccessListener implements IBaseListener<Integer>{

        @Override
        public void onError(Integer... args) {

            ctx.goToLogin();
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
