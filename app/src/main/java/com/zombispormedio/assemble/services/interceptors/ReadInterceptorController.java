package com.zombispormedio.assemble.services.interceptors;

import com.annimon.stream.IntStream;
import com.zombispormedio.assemble.utils.Utils;

import org.json.JSONObject;

import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public class ReadInterceptorController implements InterceptorControllerInterface {

    private IReadInterceptor interceptor;

    public ReadInterceptorController(IReadInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void init(@NonNull JSONObject obj) {
        if (interceptor.isApplicationActive()) {
            int[] messageIds = getMessageIDs(obj);
            interceptor.readMessages(messageIds);

            int chatId = Utils.safeGetValue("chat_id", obj);

            boolean inHome = interceptor.isInHome();
            boolean inSameChat = interceptor.isInTheSameChat(chatId);

            if (inHome) {
                interceptor.notifyHomeForChat(chatId);
            } else if (inSameChat) {
                interceptor.notifyReadToChat(messageIds);
            }
        }
    }

    private int[] getMessageIDs(@NonNull JSONObject obj) {

        int count = Utils.safeGetValue("read_count", obj);

        return IntStream.range(0, count)
                .map(i -> Utils.safeGetValue("read_" + i, obj))
                .toArray();
    }


    @Override
    public boolean permitDisplay() {
        return false;
    }

    @Override
    public NotificationCompat.Builder modifyNotificationBuilder(NotificationCompat.Builder builder) {
        return builder;
    }
}
