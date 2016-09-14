package com.zombispormedio.assemble.services.storage;


import com.zombispormedio.assemble.dao.FriendRequestProfileDAO;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestStorageService extends StorageService<FriendRequestProfileDAO, FriendRequestProfile> {

    public FriendRequestStorageService() {
      super(new LocalStorage<FriendRequestProfileDAO, FriendRequestProfile>(FriendRequestProfileDAO.class,
              new FriendRequestProfileDAO.Factory()));
    }
}
