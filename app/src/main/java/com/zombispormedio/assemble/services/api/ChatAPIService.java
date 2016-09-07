package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.ChatsResponse;
import com.zombispormedio.assemble.services.IChatService;

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
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            ChatsResponse res= JsonBinder.toChatsResponse(args[0]);
                            if(res.success){
                                handler.onSuccess(res.getResult());
                            }else{
                                handler.onError(res.error);
                            }
                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }
                    }
                })
                .get();
    }
}
