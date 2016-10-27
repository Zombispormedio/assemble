package com.zombispormedio.assemble.services.interceptors;

import com.zombispormedio.assemble.models.Message;

import org.json.JSONObject;

import android.support.v4.app.NotificationCompat;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public class MessageInterceptorController implements InterceptorControllerInterface {

    private IMessageInterceptor interceptor;

    private boolean display;

    public MessageInterceptorController(IMessageInterceptor interceptor) {
        this.interceptor = interceptor;
        display=true;
    }

    @Override
    public void init(JSONObject data) {

        if(interceptor.isApplicationActive()){
            Message message = Message.createMessage(data);
            interceptor.saveMessage(message);
            boolean inHome=interceptor.isInHome();
            boolean inSameChat=interceptor.isInTheSameChat(message.chat_id);
            display=!(inHome||inSameChat);

            if (inHome) {
                interceptor.notifyHomeForChat(message.chat_id);
            } else if (inSameChat) {
                interceptor.notifyChat();
            }
        }

    }

    @Override
    public boolean permitDisplay() {
        return display;
    }

    @Override
    public NotificationCompat.Builder modifyNotificationBuilder(NotificationCompat.Builder builder) {
        return builder.setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{1, 1, 1})
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true);
    }
}
