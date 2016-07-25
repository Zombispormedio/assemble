package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Master on 10/07/2016.
 */
public class HomeController implements IBaseController {

    private IHomeView ctx;
    private UserResources user;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        user= ModelBuilder.createUser();

    }

    public void onDrawerOpened() {
        ctx.setNavTitleText("some");
    }

    public void onSettingsMenuItemClick() {
        ctx.goToSettings();
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

    public void onSignOutMenuItemClick(){
        user.signOut();
        ctx.goToLogin();
    }

    public void onProfileMenuItemClick(){

        ctx.goToProfile();
    }
}
