package com.zombispormedio.assemble.models.services.storage;


import com.zombispormedio.assemble.wrappers.realm.dao.FriendRequestProfileDAO;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestStorageService extends StorageService<FriendRequestProfileDAO, FriendRequestProfile> {

    public FriendRequestStorageService() {
      super(new LocalStorage<>(FriendRequestProfileDAO.class,
              new FriendRequestProfileDAO.Factory()));
    }


    public FriendRequestStorageService(
            LocalStorage<FriendRequestProfileDAO, FriendRequestProfile> storage) {
        super(storage);
    }
}
