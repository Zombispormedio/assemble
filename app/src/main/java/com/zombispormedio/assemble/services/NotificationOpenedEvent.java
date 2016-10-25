package com.zombispormedio.assemble.services;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSNotificationPayload;
import com.onesignal.OneSignal;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.views.IApplicationView;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.MANY_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTIVE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationOpenedEvent extends AbstractNotificationEvent implements OneSignal.NotificationOpenedHandler {

    public NotificationOpenedEvent(IApplicationView application) {
        super(application);
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationPayload payload = result.notification.payload;
        List<OSNotificationPayload> notifications = result.notification.groupedNotifications;
        ArrayList<Message> messages = new ArrayList<>();

        if (notifications == null) {
            messages.add(Message.createMessage(payload.additionalData));
        } else {
            Stream.of(notifications)
                    .forEach(p -> messages.add(Message.createMessage(p.additionalData)));
        }
        boolean isDistinct = Message.isDistinctSender(messages);
        Class<? extends BaseActivity> classIntent = isDistinct ? HomeActivity.class : ChatActivity.class;
        Intent intent = createIntent(classIntent);
        if (!isActive()) {
            String action = isDistinct ? SEVERAL_MESSAGE_ACTION : MANY_MESSAGE_ACTION;
            intent.setAction(action);
            intent.putExtra(MESSAGES, messages);

        } else if (isDistinct) {
            intent.setAction(SEVERAL_MESSAGE_ACTIVE_ACTION);
        }

        if (!isDistinct) {
            intent.putExtra(CHAT_ID, messages.get(0).chat_id);
            intent.putExtra(FOREGROUND_NOTIFICATION, true);
        }

        startIntent(intent);

    }
}
