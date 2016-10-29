package com.zombispormedio.assemble.services;


import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.IApplicationView;

import android.content.Intent;
/**
 * Created by Xavier Serrano on 24/10/2016.
 */

public abstract class AbstractNotificationEvent {

    private IApplicationView application;

    public AbstractNotificationEvent(IApplicationView application) {
        this.application = application;
    }

    protected void sendBroadcast(Intent intent) {
        application.sendBroadcastByIntent(intent);
    }

    protected boolean isActive() {
        return application.isActive();
    }

    protected boolean isRunning(String running) {
        return application.isRunning(running);
    }

    protected PreferencesManager getPreferencesManager() {
        return application.getPreferencesManager();
    }

    protected IApplicationView getApp() {
        return application;
    }

    protected String getString(int id) {
        return application.getAppString(id);
    }

    protected int getColor(int id) {
        return application.getAppColor(id);
    }

    protected Intent createIntent(Class<? extends BaseActivity> activityClass){
        return application.createIntent(activityClass);
    }

    protected void startIntent(Intent intent){
        application.startIntent(intent);
    }
}
