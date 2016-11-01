package com.zombispormedio.assemble.models.services.storage;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.wrappers.realm.dao.ChatDAO;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatStorageService extends StorageService<ChatDAO, Chat> {

    public ChatStorageService() {
        super(new LocalStorage<>(ChatDAO.class, new ChatDAO.Factory()));
    }

    public ChatStorageService(
            LocalStorage<ChatDAO, Chat> storage) {
        super(storage);
    }
}
