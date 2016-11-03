package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.ChatEditor;
import com.zombispormedio.assemble.models.editors.MessageEditor;
import com.zombispormedio.assemble.models.services.interfaces.IChatService;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.JsonBinder;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatAPIService implements IChatService {

    private final APIConfiguration api;

    public ChatAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void getAll(final IServiceHandler<ArrayList<Chat>, Error> handler) {
        api.RestWithAuth("/chats")
                .handler(DeferUtils.deferChats(handler))
                .get();
    }

    @Override
    public void create(ChatEditor chat, IServiceHandler<Chat, Error> handler) {
        api.RestWithAuth("/chat")
                .handler(DeferUtils.deferChat(handler))
                .post(JsonBinder.fromEditChat(chat));
    }

    @Override
    public void getMessages(int id, IServiceHandler<ArrayList<Message>, Error> handler) {
        api.RestWithAuth("/chat/:id/messages")
                .params("id", id)
                .handler(DeferUtils.deferMessages(handler))
                .get();
    }

    @Override
    public void getMessages(IServiceHandler<ArrayList<Message>, Error> handler) {
        api.RestWithAuth("/chats/messages")
                .handler(DeferUtils.deferMessages(handler))
                .get();
    }

    @Override
    public void sendMessage(int id, MessageEditor message, IServiceHandler<Message, Error> handler) {
        api.RestWithAuth("/chat/:id/message")
                .params("id", id)
                .handler(DeferUtils.deferMessage(handler))
                .put(JsonBinder.fromEditMeesage(message));
    }

    @Override
    public void readMessages(int id, ChatEditor chatEditor, IServiceHandler<ArrayList<Message>, Error> handler) {
        api.RestWithAuth("/chat/:id/messages/read")
                .params("id", id)
                .handler(DeferUtils.deferMessages(handler))
                .put(JsonBinder.fromEditChat(chatEditor));
    }


}
