package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.EditChat;
import com.zombispormedio.assemble.models.editors.EditMessage;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public interface IChatService {

    void getAll(IServiceHandler<ArrayList<Chat>, Error> handler);

    void create(EditChat chat, IServiceHandler<Chat, Error> handler);

    void getMessages(int id, IServiceHandler<ArrayList<Message>, Error> handler);

    void getMessages(IServiceHandler<ArrayList<Message>, Error> handler);

    void sendMessage(int id, EditMessage message, IServiceHandler<Message, Error> handler);

}
