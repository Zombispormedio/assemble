package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.editors.EditMessage;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.HashMap;


/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    private int chatID;

    private ChatResource chatResource;

    private ProfileResource profileResource;

    private MessageSubscription messageSubscription;

    private MessageSubscriber messageSubscriber;

    public ChatController(IChatView ctx, int chatID) {
        super(ctx);
        this.ctx=ctx;
        this.chatID=chatID;
        setup();
    }

    public ChatController(IChatView ctx, HashMap<String, String> messageMap) {
        super(ctx);
        this.ctx=ctx;
        setup();

        this.chatID=setupMessage(messageMap);
    }

    private int setupMessage(HashMap<String, String> messageMap) {
        Message newMessage=Message.createMessage(messageMap);
        chatResource.storageMessage(newMessage);
        return newMessage.chat_id;
    }

    private void setup() {
        profileResource=getResourceComponent().provideProfileResource();

        chatResource=getResourceComponent().provideChatResource();

        messageSubscription =getResourceComponent().provideMessageSubscription();

        messageSubscriber =new MessageSubscriber();

        messageSubscription.addSubscriber(messageSubscriber);
    }

    @Override
    public void onCreate() {
        renderChat();
    }

    private void renderChat() {
        Chat chat=chatResource.getById(chatID);
        FriendProfile friend=chat.recipient;
        ctx.bindTitle(friend.username);

        ctx.setAvatar(friend.getLargeImageBuilder());

        ctx.bindMessages(chat.getMessages());

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


    private class MessageSubscriber extends Subscriber{

        @Override
        public void notifyChange() {
            renderChat();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageSubscription.removeSubscriber(messageSubscriber);
        ctx=null;
    }
}
