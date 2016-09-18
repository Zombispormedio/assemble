package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;

import com.zombispormedio.assemble.views.IChatsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsController extends Controller {

    private IChatsView ctx;

    private ChatResource chatResource;

    private ChatSubscription chatSubscription;

    private ChatSubscriber chatSubscriber;


    public ChatsController(IChatsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        chatResource = getResourceComponent().provideChatResource();

        chatSubscription = CurrentUser.getInstance().getChatSubscription();
        chatSubscriber = new ChatSubscriber();

        chatSubscription.addSubscriber(chatSubscriber);
    }

    @Override
    public void onCreate() {
        setupChats();
    }

    private void setupChats() {

        bindChats();
        chatSubscription.load();
    }

    public void bindChats() {
        ArrayList<Chat> chats = chatResource.getAll();

        if (chats.size() > 0) {
            ctx.bindChats(chats);
        }
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

    private class ChatSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindChats();
        }
    }

    @Override
    public void onDestroy() {
        chatSubscription.removeSubscriber(chatSubscriber);
    }
}
