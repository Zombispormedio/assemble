package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.IChatsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsController extends AbstractController {

    private IChatsView ctx;
    
    private ChatResource chatResource;

    private CurrentUser user;

    public ChatsController(IChatsView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();
        chatResource= ResourceFactory.createChatResource();
    }

    @Override
    public void onCreate() {
        setupChats();
    }

    private void setupChats() {

        if(user.getChatsCount()>0){
            ctx.bindChats(user.getChats());
        }

        getChats();
    }

    private void getChats() {
        chatResource.getAll(new IServiceHandler<ArrayList<Chat>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Chat> result) {
                user.setChats(result);
                ctx.bindChats(result);
            }
        });
    }

    public IOnClickItemListHandler<Chat> getOnClickOneTeam() {
        return new IOnClickItemListHandler<Chat>() {
            @Override
            public void onClick(int position, Chat data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }
}
