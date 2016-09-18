package com.zombispormedio.assemble.models.resources;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IProfileService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ProfileResource {

    private IStorageService<UserProfile> storage;

    private IProfileService persistence;

    @Inject
    public ProfileResource(IProfileService persistence,
            IStorageService<UserProfile> storage) {
        this.persistence = persistence;
        this.storage = storage;
    }

    public UserProfile getProfile() {
        return storage.getFirst();
    }

    public void changeAvatar(String path, final IServiceHandler<UserProfile, Error> handler) {
        persistence.changeAvatar(new File(path), new ServiceHandler<UserProfile, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(UserProfile result) {
                storage.update(result);
                CurrentUser.getInstance().getProfileSubscription().haveChanged();
                handler.onSuccess(result);
            }
        });
    }

    public void updateProfile(EditProfile profile, final IServiceHandler<UserProfile, Error> handler) {
        persistence.update(profile, new ServiceHandler<UserProfile, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(UserProfile result) {
                storage.update(result);
                CurrentUser.getInstance().getProfileSubscription().haveChanged();
                handler.onSuccess(result);
            }
        });
    }

}
