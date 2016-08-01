package com.zombispormedio.assemble.controllers;


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
public class ProfileController implements IAbstractController {

    private IProfileView ctx;

    private CurrentUser user;

    private UserResource userResource;

    public ProfileController(IProfileView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
    }

    @Override
    public void onDestroy() {
        ctx = null;
    }

    @Override
    public void onStart() {
        beforeLoadingImage();
        changeProfileImage(new ISuccessHandler() {
            @Override
            public void onSuccess() {
                afterLoadingImage();
            }
        });
    }

    @Override
    public void onStop() {

    }

    public void changeProfileImage(ISuccessHandler handler) {
        UserProfile profile = user.getProfile();

        if (Utils.presenceOf(profile.full_avatar_url)) {
            ctx.setProfileImage(profile.full_avatar_url, handler);
        } else {
            ctx.loadDefaultImage(handler);
        }

    }

    private void changeProfileImage(String link, ISuccessHandler handler) {

        if (Utils.presenceOf(link)) {
            ctx.setProfileImage(link, handler);
        } else {
            handler.onSuccess();
        }

    }


    private void beforeLoadingImage() {
        ctx.hideImageForm();
        ctx.showProgressImage();
    }

    private void afterLoadingImage() {
        ctx.showImageForm();
        ctx.hideProgressImage();
    }

    public void uploadAvatar(String path) {
        beforeLoadingImage();
        userResource.changeAvatar(path, new IServiceHandler<Result, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterLoadingImage();
            }

            @Override
            public void onSuccess(Result result) {
                changeProfileImage(result.full_avatar_url, new ISuccessHandler() {
                    @Override
                    public void onSuccess() {
                        afterLoadingImage();
                    }
                });
            }
        });
    }
}
