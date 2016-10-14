package com.zombispormedio.assemble.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.utils.PreferencesManager;


public class MessagingService extends FirebaseMessagingService {

    private PreferencesManager preferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        preferencesManager = new PreferencesManager(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Logger.d(remoteMessage.getData().containsKey("score"));
        Logger.d(preferencesManager.getString(AssembleApplication.RUNNING_ACTIVITY));
        Logger.d(preferencesManager.getInt(ChatActivity.CHAT_ID));
       // getResourceComponent().provideMessageSubscription().haveChanged();

    }

    protected ResourceComponent getResourceComponent(){
        return ((AssembleApplication)getApplication()).getResourceComponent();
    }


}
