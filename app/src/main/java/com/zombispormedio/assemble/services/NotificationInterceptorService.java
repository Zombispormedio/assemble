package com.zombispormedio.assemble.services;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.services.interceptors.IMessageInterceptor;
import com.zombispormedio.assemble.services.interceptors.IReadInterceptor;
import com.zombispormedio.assemble.services.interceptors.InterceptorControllerInterface;
import com.zombispormedio.assemble.services.interceptors.MessageInterceptorController;
import com.zombispormedio.assemble.services.interceptors.ReadInterceptorController;
import com.zombispormedio.assemble.utils.AndroidConfig;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.IApplicationView;

import org.json.JSONObject;


/**
 * Created by Xavier Serrano on 23/10/2016.
 */

public class NotificationInterceptorService extends NotificationExtenderService implements IMessageInterceptor,
        IReadInterceptor {

    private  InterceptorControllerInterface ctrl;
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        try {
            JSONObject data = notification.payload.additionalData;
            switch (notification.payload.groupKey) {
                case AndroidConfig.Groups.MESSAGE_GROUP:
                    ctrl = new MessageInterceptorController(this);
                    break;

                case AndroidConfig.Groups.READ_GROUP:
                    ctrl= new ReadInterceptorController(this);
                    break;
            }
            if (ctrl == null) {
                return true;
            }
            ctrl.init(data);

            if (ctrl.permitDisplay()) {
                OverrideSettings overrideSettings = new OverrideSettings();
                overrideSettings.extender = builder -> ctrl.modifyNotificationBuilder(builder);
                displayNotification(overrideSettings);
            }
        }catch(Exception e){
            Logger.d(e.getMessage());
        }
        return true;
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
    public void notifyChat(int messageId) {
        sendBroadcast(AndroidServiceTools.notifyChat(messageId));
    }


    @Override
    public boolean isApplicationActive() {
        return getView().isActive();
    }

    @Override
    public PreferencesManager getPreferencesManager() {
        return getView().getPreferencesManager();
    }

    @Override
    public void saveMessage(Message message) {
        sendBroadcast(AndroidServiceTools.saveMessage(message));
    }

    @Override
    public void notifyHomeForChat(int chatId, boolean read) {
        sendBroadcast(AndroidServiceTools.notifyHome(chatId, read));
    }

    @Override
    public void readMessages(int[] messageIds) {
        sendBroadcast(AndroidServiceTools.readMessages(messageIds));
    }

    @Override
    public void notifyReadToChat(int[] messageIds) {
        sendBroadcast(AndroidServiceTools.notifyReadToChat(messageIds));
    }
}
