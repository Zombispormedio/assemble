package com.zombispormedio.assemble.controllers;


import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IProfileView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ProfileController extends AbstractController {

    private IProfileView ctx;

    private CurrentUser user;

    private UserResource userResource;

    public ProfileController(IProfileView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
        beforeLoadingImage();
        changeProfileImage(new ISuccessHandler() {
            @Override
            public void onSuccess() {
                afterLoadingImage();
            }
        });
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }

    public void changeProfileImage(ISuccessHandler handler) {
        if(ctx!=null) {
            UserProfile profile = user.getProfile();

            if (Utils.presenceOf(profile.full_avatar_url)) {
                ctx.setProfileImage(profile.full_avatar_url, handler);
            } else {
                ctx.loadDefaultImage(handler);
            }
        }

    }


    private void beforeLoadingImage() {
        if(ctx!=null){
            ctx.hideImageForm();
            ctx.showProgressImage();
        }
    }

    private void afterLoadingImage() {
        if(ctx!=null) {
            ctx.showImageForm();
            ctx.hideProgressImage();
        }
    }

    public void uploadAvatar(String path) {
        beforeLoadingImage();

        userResource.changeAvatar(path, new IServiceHandler<UserProfile, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterLoadingImage();
            }

            @Override
            public void onSuccess(UserProfile result) {
                user.setProfile(result);
                changeProfileImage(new ISuccessHandler() {
                    @Override
                    public void onSuccess() {
                        afterLoadingImage();
                    }
                });
            }
        });
    }
}
