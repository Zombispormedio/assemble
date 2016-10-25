package com.zombispormedio.assemble.services;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IApplicationView;

import org.json.JSONObject;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationInterceptorService extends NotificationExtenderService {

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {

        boolean display = true;
        if (getView().isActive()) {
            Message message = Message.createMessage(notification.payload.additionalData);
            saveMessage(message);
            display = showNotificationInActive(message);
        }

        if (display) {
            overrideNotification();
        }

        return true;
    }

    private void overrideNotification() {
        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = builder -> builder.setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{1, 1, 1})
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true);

        displayNotification(overrideSettings);

    }

    private boolean showNotificationInActive(Message message) {
        boolean show = true;

        if (isInHome()) {
            notifyHome(message.chat_id);
            show = false;
        } else if (isInTheSameChat(message.chat_id)) {
            notifyChat();
            show = false;
        }

        return show;
    }

    private IApplicationView getView() {
        return (IApplicationView) getApplication();
    }

    private boolean isInHome() {
        return AndroidServiceTools.isInHome(getView());
    }

    private boolean isInTheSameChat(int chatId) {
        return AndroidServiceTools.isInTheSameChat(getView(), chatId);
    }

    private void notifyHome(int chatId) {
        sendBroadcast(AndroidServiceTools.notifyHome(chatId));
    }

    private void notifyChat() {
        sendBroadcast(AndroidServiceTools.notifyChat());
    }

    private void saveMessage(Message message) {
        sendBroadcast(AndroidServiceTools.saveMessage(message));
    }

}
