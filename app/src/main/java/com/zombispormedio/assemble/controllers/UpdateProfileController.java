package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
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
    private EditProfile.Builder editor;


    public UpdateProfileController(IUpdateProfileView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
        editor =new EditProfile.Builder(user.getProfile());
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
        ctx.openProgressDialog();

        bindEditor();

        EditProfile editProfile= editor.build();

        if(!editor.hasChanged()){
            ctx.close();
        }else {

            userResource.updateProfile(editProfile, new IServiceHandler<UserProfile, Error>() {
                @Override
                public void onError(Error error) {
                    ctx.closeProgressDialog();
                    ctx.showAlert(error.msg);
                }

                @Override
                public void onSuccess(UserProfile result) {
                    user.setProfile(result);
                    ctx.closeProgressDialog();
                    ctx.close();
                }
            });

        }

    }

    public void onClickBirthDateInput() {
        ctx.goToUpdateBirthdate(editor.getBirthdate());
    }

    public void updateBirthdate(String birthdate) {
        try {
            editor.setBirthDate(birthdate);
            ctx.setBirthDate(DateUtils.format(DateUtils.SIMPLE_SLASH_FORMAT, birthdate));
        } catch (ParseException e) {
           Logger.d(e.getMessage());
        }

    }

    public void bindEditor(){
        editor.setUsername(ctx.getUsername());
        editor.setBio(ctx.getBio());
        editor.setLocation(ctx.getLocation());
    }


    public void checkChanges(){
        bindEditor();
        if(editor.hasChanged()){
            ctx.showRejectChangesDialog(new ISuccessHandler() {
                @Override
                public void onSuccess() {
                    ctx.close();
                }
            });
        }else{
            ctx.close();
        }
    }
}
