package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.FriendProfileDAO;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendStorageService {

    private LocalStorage<FriendProfileDAO> storage;

    public FriendStorageService() {
        this.storage=new LocalStorage<>(FriendProfileDAO.class);
    }


}
