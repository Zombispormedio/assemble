package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.editors.ProfileEditor;
import com.zombispormedio.assemble.models.services.interfaces.IProfileService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.models.subscriptions.Subscription;
import com.zombispormedio.assemble.net.Error;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ProfileResource {

    private final IStorageService<UserProfile> storage;

    private final IProfileService persistence;

    private Subscription subscription;


    @Inject
    public ProfileResource(IProfileService persistence,
            IStorageService<UserProfile> storage) {
        this.persistence = persistence;
        this.storage = storage;
    }

    @Nullable
    public UserProfile getProfile() {
        return storage.getFirst();
    }

    public void changeAvatar(@NonNull String path, final IServiceHandler<UserProfile, Error> handler) {
        persistence.changeAvatar(new File(path), new ServiceHandler<UserProfile, Error>(handler) {
            @Override
            public void onSuccess(UserProfile result) {
                storage.update(result);
                haveChanged();
                super.onSuccess(result);
            }
        });
    }

    public void updateProfile(ProfileEditor profile, final IServiceHandler<UserProfile, Error> handler) {
        persistence.update(profile, new ServiceHandler<UserProfile, Error>(handler) {
            @Override
            public void onSuccess(UserProfile result) {
                storage.update(result);
                haveChanged();
                super.onSuccess(result);
            }
        });
    }

    private void haveChanged() {
        if (subscription != null) {
            subscription.haveChanged();
        }
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
