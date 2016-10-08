package com.zombispormedio.assemble.models.services.storage;

import com.zombispormedio.assemble.wrappers.realm.dao.FriendProfileDAO;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;


/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendStorageService extends StorageService<FriendProfileDAO, FriendProfile>{


    public FriendStorageService() {
        super(new LocalStorage<FriendProfileDAO, FriendProfile>(FriendProfileDAO.class, new FriendProfileDAO.Factory()));
    }
}
