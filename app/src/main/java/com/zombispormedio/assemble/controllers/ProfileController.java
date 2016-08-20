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
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IProfileView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    }

    @Override
    public void onCreate() {
        beforeLoadingImage();
        changeProfileImage(new ISuccessHandler() {
            @Override
            public void onSuccess() {
                afterLoadingImage();
            }
        });

        fillProfile();

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
        ctx.hideImageProfile();

        userResource.changeAvatar(path, new IServiceHandler<UserProfile, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterLoadingImage();
                ctx.showImageProfile();
            }

            @Override
            public void onSuccess(UserProfile result) {
                user.setProfile(result);
                changeProfileImage(new ISuccessHandler() {
                    @Override
                    public void onSuccess() {
                        ctx.showImageProfile();
                        afterLoadingImage();
                    }
                });
            }
        });
    }


    private void fillProfile(){
        if(ctx!=null){
            UserProfile profile = user.getProfile();

            if(Utils.presenceOf(profile.username)){
                ctx.setUsername(StringUtils.capitalize(profile.username));
            }else{
                ctx.setUsername("");
            }

            if(Utils.presenceOf(profile.location)){
                ctx.setLocation(profile.location);
            }else{
                ctx.setLocation("");
            }

            if(Utils.presenceOf(profile.bio)){
                ctx.setBio(profile.bio);
            }else{
                ctx.setBio("");
            }

            if(Utils.presenceOf(profile.birth_date)){
                try {
                    DateFormat inFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                    Date birth=inFormat.parse(profile.birth_date);

                    DateFormat outFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

                    ctx.setBirthDate(outFormat.format(birth));
                } catch (Exception e) {
                    Logger.d(e.getMessage());
                    ctx.setBirthDate("");
                }

            }else{
                ctx.setBirthDate("");
            }
        }
    }

    public void updateProfile() {
        ctx.goToUpdateProfile();
    }
}
