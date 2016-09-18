package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IProfileView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ProfileController extends Controller {

    private IProfileView ctx;

    private ProfileResource profileResource;

    private ProfileSubscription profileSubscription;

    private ProfileSubscriber profileSubscriber;


    public ProfileController(IProfileView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource=getResourceComponent().provideProfileResource();
        profileSubscription = getResourceComponent().provideProfileSubscription();
        profileResource.setSubscription(profileSubscription);
        profileSubscriber = new ProfileSubscriber();
        profileSubscription.addSubscriber(profileSubscriber);

    }



    @Override
    public void onCreate() {
        bindProfile();
        profileSubscription.load();

    }


    public void changeProfileImage(UserProfile profile, ISuccessHandler handler) {
        if (ctx != null) {
            String letter=StringUtils.firstLetter(profile.username);

            if (Utils.presenceOf(profile.large_avatar_url)) {
                ctx.setProfileImage(profile.large_avatar_url, letter, handler);
            } else {

                ctx.loadLetterImage(letter, handler);
            }
        }

    }


    private void beforeLoadingImage() {
        if (ctx != null) {
            ctx.hideImageForm();
            ctx.showImageProgressBar();
        }
    }

    private void afterLoadingImage() {
        if (ctx != null) {
            ctx.showImageForm();
            ctx.hideImageProgressBar();
        }
    }

    public void uploadAvatar(String path) {

        ctx.showImageProgressDialog();

        profileResource.changeAvatar(path, new ServiceHandler<UserProfile, Error>() {
            @Override
            public void onError(Error error) {
                ctx.hideImageProgressDialog();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(UserProfile result) {
                ctx.hideImageProgressDialog();
            }
        });
    }


    private void bindProfile() {

        UserProfile profile = profileResource.getProfile();

        if(profile!=null){
            AndroidUtils.fillProfile(ctx, profile);

            beforeLoadingImage();
            changeProfileImage(profile, new ISuccessHandler() {
                @Override
                public void onSuccess() {
                    afterLoadingImage();
                }
            });
        }


    }

    public void updateProfile() {
        ctx.goToUpdateProfile();
    }


    private class ProfileSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindProfile();
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
        profileSubscription.removeSubscriber(profileSubscriber);
    }


}
