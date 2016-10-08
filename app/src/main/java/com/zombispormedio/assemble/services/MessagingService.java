package com.zombispormedio.assemble.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.zombispormedio.assemble.views.IApplicationView;


public class MessagingService extends FirebaseMessagingService {

    private IApplicationView ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx=(IApplicationView)getApplication();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    }
}
