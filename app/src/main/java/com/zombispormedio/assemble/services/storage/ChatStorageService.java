package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.ChatDAO;
import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatStorageService extends StorageService<ChatDAO, Chat> {

    public ChatStorageService() {
        super(new LocalStorage<>(ChatDAO.class), new ChatDAO.Factory());
    }
}
