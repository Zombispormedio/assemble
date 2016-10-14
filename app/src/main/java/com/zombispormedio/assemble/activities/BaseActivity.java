package com.zombispormedio.assemble.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.services.api.APIConfiguration;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.services.MessagingIDService;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.activities.IBaseView;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements IBaseView {


    public static final String AUTH = "token";

    /*********Authentication*****/
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
        getPreferencesManager().remove(AUTH);
        APIConfiguration.getInstance().clearToken();
    }
    /*******************/


    /*******UI****/

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

    protected void setSubtitle(int id) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(id);
        }
    }

    protected void setSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }


    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /************************************************/

    /******************Getters***********/

    @Override
    public ResourceComponent getResourceComponent() {
        return ((AssembleApplication) getApplication()).getResourceComponent();
    }

    protected PreferencesManager getPreferencesManager() {
        return ((AssembleApplication) getApplication()).getPreferencesManager();
    }
    /************************************************/

    /**********************Messaging******************/

    @Override
    public String getMessagingId() {
        return getPreferencesManager().getString(MessagingIDService.MESSAGING_ID);
    }

    @Override
    public void removeMessagingId() {
        getPreferencesManager().remove(MessagingIDService.MESSAGING_ID);
    }






}
