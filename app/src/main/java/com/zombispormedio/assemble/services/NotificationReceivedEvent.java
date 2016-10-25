package com.zombispormedio.assemble.services;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationReceivedEvent extends AbstractNotificationEvent implements OneSignal.NotificationReceivedHandler {

    public NotificationReceivedEvent(IApplicationView application) {
        super(application);
    }

    @Override
    public void notificationReceived(OSNotification notification) {

    }






}
