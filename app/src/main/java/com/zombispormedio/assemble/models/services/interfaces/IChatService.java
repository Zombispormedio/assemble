package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.ChatEditor;
import com.zombispormedio.assemble.models.editors.MessageEditor;
import com.zombispormedio.assemble.network.Error;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public interface IChatService {

    void getAll(IServiceHandler<ArrayList<Chat>, Error> handler);

    void create(ChatEditor chat, IServiceHandler<Chat, Error> handler);

    void getMessages(int id, IServiceHandler<ArrayList<Message>, Error> handler);

    void getMessages(IServiceHandler<ArrayList<Message>, Error> handler);

    void sendMessage(int id, MessageEditor message, IServiceHandler<Message, Error> handler);

    void readMessages(int id, ChatEditor chatEditor, IServiceHandler<ArrayList<Message>, Error> handler);

}
