package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.editors.EditChat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.activities.ICreateChatView;

import java.util.ArrayList;

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

    @Override
    public void onCreate() {
        renderFriends();
    }

    private void renderFriends() {
        ArrayList<Chat> chats=chatResource.getAll();
        ArrayList<FriendProfile> friends;

        if(chats.size()>0){
            int[] friendInChatIds=new int[chats.size()];

            for (int i=0; i<chats.size(); i++) {
                friendInChatIds[i]=chats.get(i).recipient.id;
            }

            friends=friendResource.notIn(friendInChatIds);
        }else{
            friends=friendResource.getAll();
        }

        ctx.bindFriends(friends);
    }

    public void onFriend(FriendProfile data) {
        createChat(data);
    }

    private void createChat(FriendProfile data) {
        ctx.showProgress();

        chatResource.create(new EditChat(data.id), new ServiceHandler<Chat, Error>(){

            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterCreate();
            }

            @Override
            public void onSuccess(Chat result) {
                afterCreate();
            }
        });
    }

    private void afterCreate(){
        ctx.hideProgress();
        ctx.goHome();
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }
}
