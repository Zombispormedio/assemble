package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.views.IApplicationView;

import android.content.Intent;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_READ_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_READ_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_BUNDLE;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.READ;

/**
 * Created by Xavier Serrano on 25/10/2016.
 */

public class AndroidServiceTools {

    public static Intent notifyHome(int chatId) {
        return notifyHome(chatId, false);
    }

    public static Intent notifyHome(int chatId, boolean read) {
        Intent intent = new Intent();
        intent.setAction(ON_MESSAGE_NOTIFY_HOME);
        intent.putExtra(READ, read);
        intent.putExtra(CHAT_ID, chatId);
        return intent;
    }

    public static Intent saveMessage(Message message) {
        Intent intent = new Intent();
        intent.setAction(ON_MESSAGE_EVENT);
        intent.putExtra(MESSAGE_BUNDLE, message);
        return intent;
    }

    public static Intent readMessages(int[] messageIds) {
        Intent intent = new Intent();
        intent.setAction(ON_READ_EVENT);
        intent.putExtra(MESSAGES, messageIds);
        return intent;
    }


    public static Intent notifyChat(int messageId) {
        Intent intent = new Intent();
        intent.setAction(ON_MESSAGE_NOTIFY_CHAT);
        intent.putExtra(MESSAGE_ID, messageId);
        return intent;
    }

    public static Intent notifyReadToChat(int[] messageIds) {
        Intent intent = new Intent();
        intent.setAction(ON_READ_NOTIFY_CHAT);
        intent.putExtra(MESSAGES, messageIds);
        return intent;
    }


    public static boolean isInHome(IApplicationView view) {
        return view.isRunning(HomeActivity.class.getName());
    }

    public static boolean isInTheSameChat(IApplicationView view, int chatId) {
        boolean isInSomeChat = view.isRunning(ChatActivity.class.getName());
        if (isInSomeChat) {
            int currentChatID = view.getPreferencesManager().getInt(CHAT_ID);
            isInSomeChat = currentChatID == chatId;
        }

        return isInSomeChat;
    }

}
