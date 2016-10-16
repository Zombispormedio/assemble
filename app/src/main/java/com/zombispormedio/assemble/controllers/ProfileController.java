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
import com.zombispormedio.assemble.views.activities.IProfileView;

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

        profileResource = getResourceComponent().provideProfileResource();
        profileSubscription = getResourceComponent().provideProfileSubscription();
        profileResource.setSubscription(profileSubscription);
        profileSubscriber = new ProfileSubscriber();
        profileSubscription.addSubscriber(profileSubscriber);

    }


    @Override
    public void onCreate() {
        renderProfile();
        profileSubscription.load();

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


    private void renderProfile() {

        UserProfile profile = profileResource.getProfile();

        if (profile != null) {
            AndroidUtils.fillProfile(ctx, profile);
            ctx.setProfileImage(profile.getLargeImageBuilder());
        }


    }

    public void updateProfile() {
        ctx.goToUpdateProfile();
    }


    private class ProfileSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderProfile();
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
        profileSubscription.removeSubscriber(profileSubscriber);
    }


}
