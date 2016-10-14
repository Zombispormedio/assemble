package com.zombispormedio.assemble.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.utils.RunningActivity;

import android.support.v4.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;


public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Message message= getAndSaveMessage((ArrayMap<String, String>) remoteMessage.getData());

        int currentChatID=getPreferencesManager().getInt(ChatActivity.CHAT_ID);

        String who=RunningActivity.whoIsRunning;

        if(who.equals(ChatActivity.class.getName())){
            notifyChat(message, currentChatID);

        }else if(who.equals(HomeActivity.class.getName())){
            notifyHome();
        }else {
            notifyEveryWhere(message);
        }


    }

    private Message getAndSaveMessage(ArrayMap<String, String> data) {
        HashMap<String, String> hashMap=AndroidUtils.convertArrayMapToHashMap(data);

        return Message.createMessage(hashMap);
    }

    private void notifyEveryWhere(Message message) {


    }

    private void notifyHome() {
        getResourceComponent().provideChatSubscription().haveChanged();
    }

    private void notifyChat(Message message, int currentChatID) {
        if (message.chat_id==currentChatID){
            getResourceComponent().provideMessageSubscription().haveChanged();
        }else{
            notifyEveryWhere(message);
        }
    }

    protected ResourceComponent getResourceComponent(){
        return ((AssembleApplication)getApplication()).getResourceComponent();
    }

    protected PreferencesManager getPreferencesManager() {
        return ((AssembleApplication)getApplication()).getPreferencesManager();
    }


}
