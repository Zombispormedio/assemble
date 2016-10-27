package com.zombispormedio.assemble.services.interceptors;

import org.json.JSONObject;

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
    public void init(JSONObject obj) {

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
