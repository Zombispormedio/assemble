package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.ISettingsFragmentView;
import com.zombispormedio.assemble.views.ISettingsView;

/**
 * Created by Xavier Serrano on 16/07/2016.
 */
public class SettingsController extends Controller {

    private ISettingsView ctx;

    private ISettingsFragmentView fctx;

    private UserResource user;

    public SettingsController(ISettingsView ctx) {
        super(ctx);
        this.ctx = ctx;
        user = getResourceComponent().provideUserResource();
    }

    public void setFragmentView(ISettingsFragmentView fctx) {
        this.fctx = fctx;
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }

    public void signout() {
        ctx.showConfirmSignOutDialog(new SignOutDialogEvent());
    }

    private class SignOutDialogEvent implements ISuccessHandler {

        @Override
        public void onSuccess() {
            user.signOut(new SignOutServiceHandler());
        }
    }

    private class SignOutServiceHandler extends ServiceHandler<Result, Error> {

        @Override
        public void onError(Error error) {

        }

        @Override
        public void onSuccess(Result result) {
            ctx.clearAuthToken();
            ctx.showAlert(result.msg);
            ctx.goToLogin();
        }
    }
}
