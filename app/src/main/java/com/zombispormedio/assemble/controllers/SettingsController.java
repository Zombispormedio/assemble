package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler2;
import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.views.ISettingsFragmentView;
import com.zombispormedio.assemble.views.ISettingsView;

/**
 * Created by Master on 16/07/2016.
 */
public class SettingsController implements IBaseController {
    private ISettingsView ctx;
    private ISettingsFragmentView fctx;
    private UserResources user;
    public SettingsController(ISettingsView ctx) {
        this.ctx = ctx;
        user= ModelBuilder.createUser();
    }
    
    public void setFragmentView(ISettingsFragmentView fctx){
        this.fctx=fctx;
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

    public void signout() {
        ctx.showConfirmSignOutDialog(new SignOutDialogEvent());

    }

    private class SignOutDialogEvent implements IServiceHandler2<String, String> {


        @Override
        public void onError(String... args) {

        }

        @Override
        public void onSuccess(String... args) {
            user.signOut();
            ctx.goToLogin();
        }
    }
}
