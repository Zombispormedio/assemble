package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Master on 10/07/2016.
 */
public class HomeController implements IBaseController {

    private IHomeView ctx;
    private User user;
    private User.AccessVerifier verifier;

    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        user= ModelBuilder.createUser();
        verifier=user.createAccessVerifier(new AccessListener());
    }

    public void onDrawerOpened() {
        ctx.setNavTitleText(user.getEmail());
    }

    public class AccessListener implements IBaseListener<Integer>{

        @Override
        public void onError(Integer... args) {
            ctx.goToLogin();
        }

        @Override
        public void onSuccess(Integer... args) {

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

    public void onSignOutMenuItemClick(){
        user.signOut();
        ctx.goToLogin();
    }

    public void onProfileMenuItemClick(){

        ctx.goToProfile();
    }
}
