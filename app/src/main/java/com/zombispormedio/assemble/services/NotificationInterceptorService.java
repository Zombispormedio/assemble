package com.zombispormedio.assemble.services;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.services.interceptors.IMessageInterceptor;
import com.zombispormedio.assemble.services.interceptors.InterceptorControllerInterface;
import com.zombispormedio.assemble.services.interceptors.MessageInterceptorController;
import com.zombispormedio.assemble.utils.AndroidConfig;
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

public class NotificationInterceptorService extends NotificationExtenderService implements IMessageInterceptor {

    private InterceptorControllerInterface ctrl;

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        JSONObject data=notification.payload.additionalData;
        switch (notification.payload.groupKey){
            case AndroidConfig.Groups.Message: ctrl=new MessageInterceptorController(this);
                break;
        }
        if(ctrl==null){
            return true;
        }
        ctrl.init(data);

        if (ctrl.permitDisplay()) {
            overrideNotification();
        }

        return true;
    }

    private void overrideNotification() {
        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = builder -> ctrl.modifyNotificationBuilder(builder);

        displayNotification(overrideSettings);

    }


    private IApplicationView getView() {
        return (IApplicationView) getApplication();
    }

    @Override
    public boolean isInHome() {
        return AndroidServiceTools.isInHome(getView());
    }

    @Override
    public boolean isInTheSameChat(int chatId) {
        return AndroidServiceTools.isInTheSameChat(getView(), chatId);
    }

    @Override
    public void notifyHomeForChat(int chatId) {
        sendBroadcast(AndroidServiceTools.notifyHome(chatId));
    }

    @Override
    public void notifyChat() {
        sendBroadcast(AndroidServiceTools.notifyChat());
    }


    @Override
    public boolean isApplicationActive() {
        return getView().isActive();
    }

    @Override
    public void saveMessage(Message message) {
        sendBroadcast(AndroidServiceTools.saveMessage(message));
    }
}
