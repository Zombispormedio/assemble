package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.editors.EditMessage;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    private int chatID;

    private ChatResource chatResource;

    private ProfileResource profileResource;

    private ChatSubscription chatSubscription;

    private ChatSubscriber chatSubscriber;

    public ChatController(IChatView ctx, int chatID) {
        super(ctx);
        this.ctx=ctx;
        this.chatID=chatID;

        profileResource=getResourceComponent().provideProfileResource();

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

    public void onMessageSend() {
        String content=ctx.getMessageInputValue();
        EditMessage message=new EditMessage.Builder()
                .setContent(content)
                .build();

        UserProfile profile=profileResource.getProfile();

        final int index=ctx.addPendingMessage(new Message(content, profile));

        chatResource.createMessage(chatID, message, new ServiceHandler<Message, Error>(){
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(Message result) {
                ctx.addMessage(index, chatResource.getMessageById(result.id));
            }
        });

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
