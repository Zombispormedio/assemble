package com.zombispormedio.assemble.models.resources;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.ChatEditor;
import com.zombispormedio.assemble.models.editors.MessageEditor;
import com.zombispormedio.assemble.models.services.interfaces.IChatService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.models.services.storage.MessageStorageService;
import com.zombispormedio.assemble.net.Error;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatResource extends AbstractResource<Chat> {

    private final IChatService persistence;

    private final MessageStorageService messageStorage;

    @Inject
    public ChatResource(IChatService persistence, IStorageService<Chat> storage, MessageStorageService messageStorage) {
        super(storage);
        this.persistence = persistence;
        this.messageStorage = messageStorage;
    }

    public void getAll(IServiceHandler<ArrayList<Chat>, Error> handler) {
        persistence.getAll(handler);
    }


    public void create(ChatEditor chat, final IServiceHandler<Chat, Error> handler) {

        persistence.create(chat, new ServiceHandler<Chat, Error>(handler) {
            @Override
            public void onSuccess(Chat result) {
                storage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });

    }


    public Chat getById(int id) {
        return storage.getByID(id);
    }


    public void createMessage(final int id, MessageEditor message, final IServiceHandler<Message, Error> handler) {
        persistence.sendMessage(id, message, new ServiceHandler<Message, Error>(handler) {
            @Override
            public void onSuccess(@NonNull Message result) {
                messageStorage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });
    }


    public Message getMessageById(int id) {
        return messageStorage.getByID(id);
    }

    public void storeMessage(@NonNull Message message) {
        messageStorage.createOrUpdate(message);
    }

    public void storeMessages(ArrayList<Message> messages) {
        messageStorage.createOrUpdateAll(messages);
    }

    public ArrayList<Message> getMessages(int id) {
        return messageStorage.getSortedMessagesByChat(id);
    }


    public void readMessages(int id, ChatEditor chatEditor) {
        persistence.readMessages(id, chatEditor, new ServiceHandler<ArrayList<Message>, Error>() {
            @Override
            public void onError(@NonNull Error error) {
                Logger.d(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Message> result) {
                messageStorage.createOrUpdateAll(result);
                super.onSuccess(result);
            }
        });
    }

    public void haveBeenReadMessages(int[] messageIds) {
        messageStorage.read(messageIds);
    }


}
