package com.zombispormedio.assemble.activities;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.services.api.APIConfiguration;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.activities.IBaseView;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_READ_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.AUTH;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_BUNDLE;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    protected ArrayList<BroadcastReceiver> receivers;


    @Override
    protected void onStart() {
        setupReceivers();
        super.onStart();
    }

    @Override
    protected void onStop() {
        slashReceivers();
        super.onStop();
    }

    /********* Authentication *****/
    public String getAuthToken() {
        return getPreferencesManager().getString(AUTH);
    }

    @Override
    public void setAuthToken(String token) {
        getPreferencesManager().set(AUTH, token);
        APIConfiguration.getInstance().setToken(token);
    }

    @Override
    public void clearAuthToken() {
        APIConfiguration.getInstance().clearToken();
        getPreferencesManager().clear();
    }
    /*******************/


    /******* UI ****/

    public void showAlert(String msg) {
        AndroidUtils.showAlert(this, msg);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setHomeUpIcon(int id) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(id);
        }
    }

    protected void bindActivity(Activity target) {
        ButterKnife.bind(target);
    }

    protected void setToolbarSubtitle(int id) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(id);
        }
    }

    protected void setToolbarSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }


    protected void setToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /************************************************/

    /****************** Getters ***********/

    @Override
    public ResourceComponent getResourceComponent() {
        return ((AssembleApplication) getApplication()).getResourceComponent();
    }


    public PreferencesManager getPreferencesManager() {
        return ((AssembleApplication) getApplication()).getPreferencesManager();
    }
    /************************************************/

    /********************** Messaging ******************/





    protected void setupReceivers() {
        receivers= new ArrayList<>();
        configureReceiver(new MessageSavingReceiver(), ON_MESSAGE_EVENT);
        configureReceiver(new ReadReceiver(), ON_READ_EVENT);
    }


    protected void configureReceiver(BroadcastReceiver receiver, String action){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(receiver, intentFilter);
        receivers.add(receiver);
    }

    protected void slashReceivers() {
        for (BroadcastReceiver receiver : receivers) {
            unregisterReceiver(receiver);
        }
    }


    private class MessageSavingReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data = intent.getExtras();
            Message message = data.getParcelable(MESSAGE_BUNDLE);
            getResourceComponent().provideChatResource().storeMessage(message);
        }
    }

    private class ReadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data = intent.getExtras();
            getResourceComponent().provideChatResource().haveBeenReadMessages(data.getIntArray(MESSAGES));
        }
    }




}
