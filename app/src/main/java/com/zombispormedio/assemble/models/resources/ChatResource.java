package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.EditChat;
import com.zombispormedio.assemble.models.editors.EditMessage;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IChatService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatResource extends ConceptResource<Chat> {

    private IChatService persistence;

    private IStorageService<Message> storageMessage;

    @Inject
    public ChatResource(IChatService persistence, IStorageService<Chat> storage, IStorageService<Message> storageMessage) {
        super(storage);
        this.persistence = persistence;
        this.storageMessage = storageMessage;
    }

    public void getAll(IServiceHandler<ArrayList<Chat>, Error> handler) {
        persistence.getAll(handler);
    }


    public void create(EditChat chat, final IServiceHandler<Chat, Error> handler) {

        persistence.create(chat, new ServiceHandler<Chat, Error>() {
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Chat result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });

    }

    public Chat getById(int id) {
        return storage.getByID(id);
    }


    public void createMessage(final int id, EditMessage message, final IServiceHandler<Message, Error> handler) {
        persistence.sendMessage(id, message, new ServiceHandler<Message, Error>() {
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Message result) {
                storageMessage.create(result);
                handler.onSuccess(result);
            }
        });
    }

    public Message getMessageById(int id) {
        return storageMessage.getByID(id);
    }


}
