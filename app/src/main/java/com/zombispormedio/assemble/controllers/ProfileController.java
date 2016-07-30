package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IProfileView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ProfileController implements IBaseController {

    private IProfileView ctx;
    private CurrentUser user;
    private UserResource userResource;

    public ProfileController(IProfileView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();

        userResource = ResourceFactory.createUser();


    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {
        ctx.hideImageForm();
        ctx.showProgressImage();
        changeProfileImage( new IPromiseHandler() {
            @Override
            public void onSuccess(String... args) {
                ctx.showImageForm();
                ctx.hideProgressImage();
            }
        });
    }

    public void changeProfileImage(IPromiseHandler handler){
        UserProfile profile=user.getProfile();
        if(ctx!=null){
            ctx.setProfileImage(profile.full_avatar_url, handler);
        }

    }

    public void changeProfileImage(){
        UserProfile profile=user.getProfile();
        if(ctx!=null){
            ctx.setProfileImage(profile.full_avatar_url);
        }

    }

    @Override
    public void onStop() {

    }
}
