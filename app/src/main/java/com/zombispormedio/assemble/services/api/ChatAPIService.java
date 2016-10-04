package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.editors.EditChat;
import com.zombispormedio.assemble.models.editors.EditMessage;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.services.interfaces.IChatService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatAPIService implements IChatService {

    private APIConfiguration api;

    public ChatAPIService() {
        api=APIConfiguration.getInstance();
    }

    @Override
    public void getAll(final IServiceHandler<ArrayList<Chat>, Error> handler) {
        api.RestWithAuth("/chats")
                .handler(DeferUtils.deferChats(handler))
                .get();
    }

    @Override
    public void create(EditChat chat, IServiceHandler<Chat, Error> handler) {
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
    public void sendMessage(int id, EditMessage message, IServiceHandler<Message, Error> handler) {
        api.RestWithAuth("/chat/:id/message")
                .params("id", id)
                .handler(DeferUtils.deferMessage(handler))
                .put(JsonBinder.fromEditMeesage(message));
    }


}
