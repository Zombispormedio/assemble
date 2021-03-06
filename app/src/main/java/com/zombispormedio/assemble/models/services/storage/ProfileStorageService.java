package com.zombispormedio.assemble.models.services.storage;

import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.models.services.storage.dao.UserProfileDAO;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ProfileStorageService extends StorageService<UserProfileDAO, UserProfile> {

    public ProfileStorageService() {
        super(new LocalStorage<>(UserProfileDAO.class, new UserProfileDAO.Factory()));
    }

    public ProfileStorageService(
            LocalStorage<UserProfileDAO, UserProfile> storage) {
        super(storage);
    }
}
