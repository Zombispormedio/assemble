package com.zombispormedio.assemble.services;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.RunningActivity;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IApplicationView;

import android.content.Intent;

import java.util.HashMap;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_BUNDLE;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationReceivedEvent extends AbstractNotificationEvent implements OneSignal.NotificationReceivedHandler {


    public NotificationReceivedEvent(IApplicationView application) {
        super(application);
    }

    @Override
    public void notificationReceived(OSNotification notification) {

        if(isActive()){
            HashMap<String, String> data = Utils.convertJSONObjectToHashMap(notification.payload.additionalData);
            Message message = Message.createMessage(data);
            saveMessage(message);
            configureMessage(message);
        }
    }


    private void configureMessage(Message message){

        if(isRunning(HomeActivity.class.getName())){
            notifyHome(message);
        }else if(isRunning(ChatActivity.class.getName())){
            notifyChat(message);
        }else {
            notifyEveryWhere(message);
        }


    }

    private void notifyChat(Message message) {
        int currentChatID=getPreferencesManager().getInt(CHAT_ID);

        if (message.chat_id==currentChatID){
            Intent intent=new Intent();
            intent.setAction(ON_MESSAGE_NOTIFY_CHAT);
            sendBroadcast(intent);
        }else{
            notifyEveryWhere(message);
        }
    }


    private void notifyEveryWhere(Message message) {

    }

    private void notifyHome(Message message) {
        Intent intent = new Intent();
        intent.setAction(ON_MESSAGE_NOTIFY_HOME);
        intent.putExtra(CHAT_ID, message.chat_id);
        sendBroadcast(intent);
    }


    private void saveMessage(Message message) {
        Intent intent=new Intent();
        intent.setAction(ON_MESSAGE_EVENT);
        intent.putExtra(MESSAGE_BUNDLE, message);
        sendBroadcast(intent);
    }

}
