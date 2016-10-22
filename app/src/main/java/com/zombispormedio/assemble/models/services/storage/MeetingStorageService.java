package com.zombispormedio.assemble.models.services.storage;


import com.zombispormedio.assemble.wrappers.realm.dao.MeetingDAO;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class MeetingStorageService extends StorageService<MeetingDAO, Meeting> {

    public MeetingStorageService() {
        super(new LocalStorage<>(MeetingDAO.class, new MeetingDAO.Factory()));
    }

    public MeetingStorageService(
            LocalStorage<MeetingDAO, Meeting> storage) {
        super(storage);
    }
}
