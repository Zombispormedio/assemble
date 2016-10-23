package com.zombispormedio.assemble.services;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationInterceptorService extends NotificationExtenderService {

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        return false;
    }
}
