package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.views.ICreateChatView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateChatController extends Controller {

    private ICreateChatView ctx;

    private ChatResource chatResource;

    private FriendResource friendResource;

    public CreateChatController(ICreateChatView ctx) {
        super(ctx);
        this.ctx = ctx;

        chatResource=getResourceComponent().provideChatResource();
        friendResource=getResourceComponent().provideFriendResource();
    }
}
