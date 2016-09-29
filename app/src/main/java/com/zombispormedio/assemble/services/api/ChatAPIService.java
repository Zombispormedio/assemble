package com.zombispormedio.assemble.services.api;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.EditChat;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.ChatsResponse;
import com.zombispormedio.assemble.services.interfaces.IChatService;

import java.io.IOException;
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


}
