package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.views.IUpdateProfileView;

import java.text.ParseException;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public class UpdateProfileController extends Controller {

    private IUpdateProfileView ctx;

    private ProfileResource profileResource;

    private ProfileSubscription profileSubscription;

    private ProfileSubscriber profileSubscriber;

    private EditProfile.Builder editor;


    public UpdateProfileController(IUpdateProfileView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource = getResourceComponent().provideProfileResource();
        profileSubscription = getResourceComponent().provideProfileSubscription();
        profileResource.setSubscription(profileSubscription);
        profileSubscriber = new ProfileSubscriber();
        profileSubscription.addSubscriber(profileSubscriber);

        editor = new EditProfile.Builder();
    }

    @Override
    public void onCreate() {
        bindProfile();
    }

    private void bindProfile() {
        UserProfile profile = profileResource.getProfile();
        if (profile != null) {
            editor.setProfile(profile);
            AndroidUtils.fillProfile(ctx, profile);
        }

    }

    public void save() {
        ctx.openProgressDialog();

        bindEditor();

        EditProfile editProfile = editor.build();

        if (!editor.hasChanged()) {
            ctx.closeProgressDialog();
            ctx.close();
        } else {

            profileResource.updateProfile(editProfile, new ServiceHandler<UserProfile, Error>() {
                @Override
                public void onError(Error error) {
                    ctx.closeProgressDialog();
                    ctx.showAlert(error.msg);
                }

                @Override
                public void onSuccess(UserProfile result) {
                    ctx.closeProgressDialog();
                    ctx.close();
                }
            });

        }

    }

    public void editBirthDate() {
        ctx.goToUpdateBirthdate(editor.getBirthdate());
    }

    public void updateBirthdate(String birthdate) {
        try {
            editor.setBirthDate(birthdate);
            ctx.setBirthDate(birthdate);
        } catch (ParseException e) {
            Logger.d(e.getMessage());
        }

    }

    public void bindEditor() {
        editor.setUsername(ctx.getUsername());
        editor.setBio(ctx.getBio());
        editor.setLocation(ctx.getLocation());
    }


    public void checkChanges() {
        bindEditor();
        if (editor.hasChanged()) {
            ctx.showRejectChangesDialog(new ISuccessHandler() {
                @Override
                public void onSuccess() {
                    ctx.close();
                }
            });
        } else {
            ctx.close();
        }
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
