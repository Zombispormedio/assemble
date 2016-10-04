package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;
import java.util.Arrays;

import javax.security.auth.login.LoginException;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    private int chatID;

    private ChatResource chatResource;

    private ChatSubscription chatSubscription;

    private ChatSubscriber chatSubscriber;

    public ChatController(IChatView ctx, int chatID) {
        super(ctx);
        this.ctx=ctx;
        this.chatID=chatID;

        chatResource=getResourceComponent().provideChatResource();

        chatSubscription=getResourceComponent().provideChatSubscription();

        chatSubscriber=new ChatSubscriber();

        chatSubscription.addSubscriber(chatSubscriber);

    }

    @Override
    public void onCreate() {
        bindChat();
    }

    private void bindChat() {
        Chat chat=chatResource.getById(chatID);
        FriendProfile friend=chat.recipient;
        String friendName=friend.username;

        ctx.bindTitle(friendName);

        ctx.setAvatar(friend.large_avatar_url, StringUtils.firstLetter(friendName));

        ctx.bindMessages(new ArrayList<>(Arrays.asList(chat.messages)));

    }

    private class ChatSubscriber extends Subscriber{

        @Override
        public void notifyChange() {
            bindChat();
        }
    }

    @Override
    public void onDestroy() {
        chatSubscription.removeSubscriber(chatSubscriber);
    }
}
