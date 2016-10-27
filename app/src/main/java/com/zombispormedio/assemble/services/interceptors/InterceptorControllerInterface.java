package com.zombispormedio.assemble.services.interceptors;

import org.json.JSONObject;

import android.support.v4.app.NotificationCompat;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface InterceptorControllerInterface {

    void init(JSONObject obj);

    boolean permitDisplay();

    NotificationCompat.Builder modifyNotificationBuilder(NotificationCompat.Builder builder);

}
