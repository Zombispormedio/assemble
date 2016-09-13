package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ProfileStorageService extends StorageService<UserProfileDAO, UserProfile>  {

    public ProfileStorageService() {
        super(new LocalStorage<>(UserProfileDAO.class), new UserProfileDAO.Factory());
    }


}
