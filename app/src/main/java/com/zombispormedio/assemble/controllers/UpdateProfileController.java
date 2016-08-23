package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IUpdateProfileView;

import javax.security.auth.login.LoginException;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public class UpdateProfileController extends AbstractController {

    private IUpdateProfileView ctx;

    private CurrentUser user;
    private UserResource userResource;

    public UpdateProfileController(IUpdateProfileView ctx) {
        this.ctx = ctx;

        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fillProfile();


    }

    private void fillProfile(){
        AndroidUtils.fillProfile(ctx,  user.getProfile());
    }

    public void onClickSaveButton() {


    }

    public void onClickBirthDateInput() {
        ctx.goToUpdateBirthdate();
    }

}
