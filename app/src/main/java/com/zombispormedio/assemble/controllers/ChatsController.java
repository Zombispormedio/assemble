package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;

import com.zombispormedio.assemble.views.fragments.IChatsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsController extends Controller {

    private final IChatsView ctx;

    private final ChatResource chatResource;

    private final ChatSubscription chatSubscription;

    private final ChatSubscriber chatSubscriber;

    private final MessageSubscription messageSubscription;

    private final MessageSubscriber messageSubscriber;

    private boolean refreshing;

    public ChatsController(IChatsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        chatResource = getResourceComponent().provideChatResource();

        chatSubscription = getResourceComponent().provideChatSubscription();
        messageSubscription=getResourceComponent().provideMessageSubscription();
        chatSubscriber = new ChatSubscriber();
        chatSubscription.addSubscriber(chatSubscriber);
        messageSubscriber=new MessageSubscriber();
        messageSubscription.addSubscriber(messageSubscriber);

        refreshing = false;
    }

    @Override
    public void onStart() {
        renderChats();
    }

    private void renderChats() {
        ArrayList<Chat> chats = chatResource.getAll();
        ctx.bindChats(chats);
    }


    public void onChatItem(int position, Chat chat) {
        ctx.goToChat(chat.id);
    }

    public void onRefresh() {
        refreshing = true;
        messageSubscription.load();
    }

    private class ChatSubscriber extends Subscriber {
        @Override
        public void notifyChange() {
            renderChats();
            finishRefresh();
        }

        @Override
        public void notifyFail() {
            finishRefresh();
        }

        @Override
        public void notifyOneChange(int id) {
            Chat chat=chatResource.getById(id);
            ctx.updateChat(chat);
            messageSubscription.load();
        }
    }

    private class MessageSubscriber extends Subscriber {
        @Override
        public void notifyChange() {
            if(refreshing){
                chatSubscription.load();}
            else{
                renderChats();
            }
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
        super.onDestroy();
        chatSubscription.removeSubscriber(chatSubscriber);
        messageSubscription.removeSubscriber(messageSubscriber);
    }
}
