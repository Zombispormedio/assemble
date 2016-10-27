package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.editors.ProfileEditor;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.views.activities.IUpdateProfileView;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public class UpdateProfileController extends Controller {

    private IUpdateProfileView ctx;

    private final ProfileResource profileResource;

    private final ProfileSubscription profileSubscription;

    private final ProfileSubscriber profileSubscriber;

    private final ProfileEditor.Builder editor;


    public UpdateProfileController(IUpdateProfileView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource = getResourceComponent().provideProfileResource();
        profileSubscription = getResourceComponent().provideProfileSubscription();
        profileResource.setSubscription(profileSubscription);
        profileSubscriber = new ProfileSubscriber();
        profileSubscription.addSubscriber(profileSubscriber);

        editor = new ProfileEditor.Builder();
    }

    @Override
    public void onCreate() {
        renderProfile();
    }

    private void renderProfile() {
        UserProfile profile = profileResource.getProfile();
        if (profile != null) {
            editor.setProfile(profile);
            AndroidUtils.fillProfile(ctx, profile);
        }

    }

    public void save() {
        ctx.openProgressDialog();

        bindEditor();

        ProfileEditor profileEditor = editor.build();

        if (!editor.hasChanged()) {
            ctx.closeProgressDialog();
            ctx.close();
        } else {

            profileResource.updateProfile(profileEditor, new ServiceHandler<UserProfile, Error>() {
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
        editor.setBirthDate(birthdate);
        ctx.setBirthDate(new ISODate(birthdate), "");
    }

    private void bindEditor() {
        editor.setUsername(ctx.getUsername());
        editor.setBio(ctx.getBio());
        editor.setLocation(ctx.getLocation());
    }


    public void checkChanges() {
        bindEditor();
        if (editor.hasChanged()) {
            ctx.showRejectChangesDialog(() -> ctx.close());
        } else {
            ctx.close();
        }
    }

    private class ProfileSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderProfile();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
        profileSubscription.removeSubscriber(profileSubscriber);
    }
}
