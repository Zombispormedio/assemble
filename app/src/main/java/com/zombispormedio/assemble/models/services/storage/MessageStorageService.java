package com.zombispormedio.assemble.models.services.storage;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.wrappers.realm.dao.MessageDAO;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class MessageStorageService  extends StorageService<MessageDAO, Message> {

    public MessageStorageService() {
        super(new LocalStorage<>(MessageDAO.class, new MessageDAO.Factory()));
    }

    public MessageStorageService(
            LocalStorage<MessageDAO, Message> storage) {
        super(storage);
    }

    public ArrayList<Message> getSortedMessagesByChat(int chatId){
        ArrayList<MessageDAO> daos=storage.findByAndSort("chat_id", chatId, "created_at");
        return Stream.of(daos)
                .map(MessageDAO::toModel)
                .collect(Collectors.toCollection(ArrayList<Message>::new));
    }

    public Message getLastMessage(int chatId){
        MessageDAO dao=storage.findOneByAndSort("chat_id", chatId, "created_at");
        return dao!=null?dao.toModel():null;
    }
}
