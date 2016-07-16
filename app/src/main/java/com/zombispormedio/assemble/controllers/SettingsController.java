package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.listeners.IListener;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.views.ISettingsFragmentView;
import com.zombispormedio.assemble.views.ISettingsView;

/**
 * Created by Master on 16/07/2016.
 */
public class SettingsController implements IBaseController {
    private ISettingsView ctx;
    private ISettingsFragmentView fctx;
    private User user;
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

    private class SignOutDialogEvent implements IListener{

        @Override
        public void onError() {

        }

        @Override
        public void onSuccess() {
           user.signOut();
           ctx.goToLogin();

        }
    }
}
