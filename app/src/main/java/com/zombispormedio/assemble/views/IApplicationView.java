package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.utils.PreferencesManager;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public interface IApplicationView {

    ResourceComponent getResourceComponent();

    boolean isConnected();

    void sendBroadcastByIntent(Intent intent);

    PreferencesManager getPreferencesManager();

    String getAppString(int id);

    int getAppColor(int id);

    boolean isRunning(String running);

    boolean isActive();

}
