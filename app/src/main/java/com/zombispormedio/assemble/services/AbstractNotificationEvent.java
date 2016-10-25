package com.zombispormedio.assemble.services;


import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.utils.RunningActivity;
import com.zombispormedio.assemble.views.IApplicationView;

import android.content.Intent;
import android.os.Looper;

/**
 * Created by Xavier Serrano on 24/10/2016.
 */

public abstract class AbstractNotificationEvent {

    private IApplicationView application;

    public AbstractNotificationEvent(IApplicationView application) {
        this.application = application;
    }

    protected void sendBroadcast(Intent intent){
        application.sendBroadcastByIntent(intent);
    }

    protected boolean isActive(){
        return !RunningActivity.whoIsRunning.isEmpty();
    }

    protected boolean isRunning(String running){
        return RunningActivity.whoIsRunning.equals(running);
    }

    protected PreferencesManager getPreferencesManager() {
        return application.getPreferencesManager();
    }
}
