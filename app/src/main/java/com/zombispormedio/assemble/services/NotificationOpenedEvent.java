package com.zombispormedio.assemble.services;

import com.annimon.stream.Stream;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSNotificationPayload;
import com.onesignal.OneSignal;
import com.zombispormedio.assemble.services.events.INotificationEventController;
import com.zombispormedio.assemble.services.events.MessageEventController;
import com.zombispormedio.assemble.utils.AndroidConfig;
import com.zombispormedio.assemble.views.IApplicationView;

import org.json.JSONObject;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

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
            case AndroidConfig.Groups.MESSAGE_GROUP: ctrl=new MessageEventController();
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
