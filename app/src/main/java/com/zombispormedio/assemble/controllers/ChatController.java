package com.zombispormedio.assemble.controllers;

import com.annimon.stream.Stream;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.editors.ChatEditor;
import com.zombispormedio.assemble.models.editors.MessageEditor;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;


/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    private final int chatID;

    private ChatResource chatResource;

    private ProfileResource profileResource;

    private MessageSubscription messageSubscription;

    private MessageSubscriber messageSubscriber;

    public ChatController(IChatView ctx, int chatID) {
        super(ctx);
        this.ctx = ctx;
        this.chatID = chatID;
        setup();
    }

    public ChatController(IChatView ctx, int chatID, ArrayList<Message> messages) {
        super(ctx);
        this.ctx = ctx;
        setup();
        this.chatID = chatID;
        chatResource.storeMessages(messages);
    }


    private void setup() {
        profileResource = getResourceComponent().provideProfileResource();

        chatResource = getResourceComponent().provideChatResource();

        messageSubscription = getResourceComponent().provideMessageSubscription();

        messageSubscriber = new MessageSubscriber();

        messageSubscription.addSubscriber(messageSubscriber);
    }

    @Override
    public void onCreate() {
        renderChat();
    }


    private void renderChat() {
        Chat chat = chatResource.getById(chatID);
        FriendProfile friend = chat.recipient;
        ctx.bindTitle(friend.username);

        ctx.setAvatar(friend.getLargeImageBuilder());

        ArrayList<Message> messages = chatResource.getMessages(chatID);

        ctx.bindMessages(messages);

        readMessages(messages);


    }


    public void onMessageSend() {
        String content = ctx.getMessageInputValue();
        MessageEditor message = new MessageEditor.Builder()
                .setContent(content)
                .build();

        UserProfile profile = profileResource.getProfile();

        final int index = ctx.addPendingMessage(new Message(content, profile, ISODate.Now().toString()));

        chatResource.createMessage(chatID, message, new ServiceHandler<Message, Error>() {
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

    private void readMessages(ArrayList<Message> messages) {
        int[] messagesIDs = Stream.of(messages)
                .filter(item -> !item.is_read && item.sender instanceof FriendProfile)
                .mapToInt(item -> item.id)
                .toArray();
        if (messagesIDs.length > 0) {
            ChatEditor.Builder builder = new ChatEditor.Builder()
                    .setMessages(messagesIDs);
            chatResource.readMessages(chatID, builder.build(), new ServiceHandler<ArrayList<Message>, Error>() {
                @Override
                public void onSuccess(ArrayList<Message> result) {
                    ctx.showAlert("read");
                }

                @Override
                public void onError(Error error) {
                    ctx.showAlert(error.msg);
                }
            });
        }

    }


    private class MessageSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderChat();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageSubscription.removeSubscriber(messageSubscriber);
        ctx = null;
    }
}
