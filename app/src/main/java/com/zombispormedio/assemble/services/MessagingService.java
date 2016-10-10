package com.zombispormedio.assemble.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;


public class MessagingService extends FirebaseMessagingService {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Logger.d(remoteMessage.getNotification().getBody());
    }
}
