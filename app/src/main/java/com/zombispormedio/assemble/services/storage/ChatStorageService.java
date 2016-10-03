package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.ChatDAO;
import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.dao.MessageDAO;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatStorageService extends StorageService<ChatDAO, Chat> {

    public ChatStorageService() {
        super(new LocalStorage<ChatDAO, Chat>(ChatDAO.class, new ChatDAO.Factory()));
    }


    public void addMessages(int id, ArrayList<Message> messages){
        ChatDAO chatDAO=storage.getById(id);

        MessageStorageService messageService=new MessageStorageService();
        ArrayList<MessageDAO> messageDAOs=new ArrayList<>();


        for (Message msg: messages) {
            int messageId = msg.id;

            MessageDAO messageDAO = messageService.getStorage().getById(messageId);

            if (messageDAO == null) {
                messageDAO = new MessageDAO();
            }

            messageService.getStorage().update(messageDAO, msg);

            messageDAOs.add(messageDAO);
        }

        storage.begin();
        chatDAO.addMessages(messageDAOs);
        storage.commit();
    }
}
