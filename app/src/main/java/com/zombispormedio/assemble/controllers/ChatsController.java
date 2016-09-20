package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.resources.ChatResource;
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

    private boolean refreshing;

    public ChatsController(IChatsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        chatResource = getResourceComponent().provideChatResource();

        chatSubscription = getResourceComponent().provideChatSubscription();
        chatSubscriber = new ChatSubscriber();
        chatSubscription.addSubscriber(chatSubscriber);
        refreshing = false;
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
        ctx.bindChats(chats);

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

    public void onRefresh() {
        refreshing = true;
        chatSubscription.load();
    }

    private class ChatSubscriber extends Subscriber {
        @Override
        public void notifyChange() {
            bindChats();
            finishRefresh();
        }

        @Override
        public void notifyFail() {
            finishRefresh();
        }
    }

    private void finishRefresh() {
        if (refreshing) {
            refreshing = false;
            ctx.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        chatSubscription.removeSubscriber(chatSubscriber);
    }
}
