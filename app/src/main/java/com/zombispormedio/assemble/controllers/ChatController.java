package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    private int chatID;

    private ChatResource chatResource;

    public ChatController(IChatView ctx, int chatID) {
        super(ctx);
        this.ctx=ctx;
        this.chatID=chatID;

        chatResource=getResourceComponent().provideChatResource();
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

    }
}
