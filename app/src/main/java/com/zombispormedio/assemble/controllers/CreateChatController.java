package com.zombispormedio.assemble.controllers;

import com.annimon.stream.Stream;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.editors.ChatEditor;
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

    private final ChatResource chatResource;

    private final FriendResource friendResource;

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
          int[] friendInChatIds=Stream.of(chats)
                  .map(item->item.recipient.id)
                  .mapToInt(i->i)
                  .toArray();

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

        ChatEditor.Builder builder=new ChatEditor.Builder()
                .setFriend(data.id);

        chatResource.create(builder.build(), new ServiceHandler<Chat, Error>(){

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
        super.onDestroy();
        ctx=null;
    }
}
