package com.zombispormedio.assemble.services;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.orhanobut.logger.Logger;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationOpenedEvent implements OneSignal.NotificationOpenedHandler {

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        Logger.d(result);
    }
}
