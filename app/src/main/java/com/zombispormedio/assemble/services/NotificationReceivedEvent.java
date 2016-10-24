package com.zombispormedio.assemble.services;

import com.annimon.stream.Stream;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.IAssembleApplication;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.services.storage.MessageStorageService;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.wrappers.realm.dao.MessageDAO;

import org.json.JSONException;

import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;

import io.realm.Realm;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_BUNDLE;

/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationReceivedEvent extends AbstractNotificationEvent implements OneSignal.NotificationReceivedHandler {

    private AssembleApplication application;

    public NotificationReceivedEvent(AssembleApplication application) {
        this.application = application;
    }

    @Override
    public void notificationReceived(OSNotification notification) {

        HashMap<String, String> data = Utils.convertJSONObjectToHashMap(notification.payload.additionalData);
        Message message = Message.createMessage(data);
        Intent intent = new Intent();
        intent.setAction(ON_MESSAGE_EVENT);
        intent.putExtra(MESSAGE_BUNDLE, message);
        application.sendBroadcast(intent);
        Intent intent2 = new Intent();
        intent2.setAction(ON_MESSAGE_NOTIFY_HOME);
        intent2.putExtra(CHAT_ID, message.chat_id);
        application.sendBroadcast(intent2);


    }

}
