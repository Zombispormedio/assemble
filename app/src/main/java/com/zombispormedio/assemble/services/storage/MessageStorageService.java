package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.MessageDAO;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class MessageStorageService  extends StorageService<MessageDAO, Message> {

    public MessageStorageService() {
        super(new LocalStorage<MessageDAO, Message>(MessageDAO.class, new MessageDAO.Factory()));
    }

}
