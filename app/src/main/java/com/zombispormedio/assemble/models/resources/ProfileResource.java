package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ProfileResource {

    private IStorageService<UserProfile> storage;

    public ProfileResource(
            IStorageService<UserProfile> storage) {
        this.storage = storage;
    }

    public UserProfile getProfile() {
        return storage.getFirst();
    }

}
