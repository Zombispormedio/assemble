package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.views.IUpdateProfileView;

import java.text.ParseException;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public class UpdateProfileController extends AbstractController {

    private IUpdateProfileView ctx;

    private CurrentUser user;
    private UserResource userResource;
    private EditProfile.Builder edit;


    public UpdateProfileController(IUpdateProfileView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
        edit =new EditProfile.Builder(user.getProfile());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fillProfile();

    }

    private void fillProfile(){
        AndroidUtils.fillProfile(ctx,  user.getProfile());
    }

    public void onSave() {
        Logger.d(ctx.getUsername()==null);
    }

    public void onClickBirthDateInput() {
        ctx.goToUpdateBirthdate(edit.getBirthdate());
    }

    public void updateBirthdate(String birthdate) {
        try {
            edit.setBirthDate(birthdate);
            ctx.setBirthDate(DateUtils.format(DateUtils.SIMPLE_SLASH_FORMAT, birthdate));
        } catch (ParseException e) {
           Logger.d(e.getMessage());
        }

    }
}
