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
import com.zombispormedio.assemble.services.events.INotificationEventController;
import com.zombispormedio.assemble.services.events.MessageEventController;
import com.zombispormedio.assemble.utils.AndroidConfig;
import com.zombispormedio.assemble.views.IApplicationView;

import org.json.JSONObject;

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

    private INotificationEventController ctrl;

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationPayload payload = result.notification.payload;
        List<OSNotificationPayload> notifications = result.notification.groupedNotifications;
        ArrayList<JSONObject> data = new ArrayList<>();

        if (notifications == null) {
            data.add(payload.additionalData);
        } else {
            Stream.of(notifications)
                    .forEach(p -> data.add(p.additionalData));
        }

        switch (payload.groupKey){
            case AndroidConfig.Groups.Message: ctrl=new MessageEventController();
                break;
        }
        if(ctrl==null){
            return;
        }
        ctrl.init(data);

        if(ctrl.permitIntent()){
            Intent intent = createIntent(ctrl.getIntentClass());

            intent=ctrl.modifyIntent(intent, isActive());

            startIntent(intent);
        }


    }
}
