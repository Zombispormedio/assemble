package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.utils.PreferencesManager;

import android.content.Intent;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public interface IApplicationView {

    ResourceComponent getResourceComponent();

    boolean isConnected();

    void sendBroadcastByIntent(Intent intent);

    PreferencesManager getPreferencesManager();

}
