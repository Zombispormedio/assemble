package com.zombispormedio.assemble;


import com.google.firebase.crash.FirebaseCrash;

import com.squareup.leakcanary.LeakCanary;
import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.components.DaggerResourceComponent;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.models.modules.SubscriptionModule;
import com.zombispormedio.assemble.models.services.api.APIConfiguration;
import com.zombispormedio.assemble.net.ConnectionState;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.utils.RunningActivity;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import net.danlew.android.joda.JodaTimeAndroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class AssembleApplication extends Application implements IAssembleApplication {


    private final static String[] REFERENCES_TO_RESET_ON_START = new String[]{
            HomeActivity.LOADED,
            ChatActivity.CHAT_ID
    };

    private ResourceComponent resourceComponent;
    private PreferencesManager preferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
        JodaTimeAndroid.init(this);
        ConnectionState.getInstance().setContext(this);
        registerActivityLifecycleCallbacks(new RunningActivity());

        preferencesManager = new PreferencesManager(this);

        resourceComponent = DaggerResourceComponent.builder()
                .resourceModule(new ResourceModule())
                .subscriptionModule(new SubscriptionModule())
                .build();

        setupUncaughtException();

        setupPreferences();

        setupLocalStorage();

        setupAPI();
    }



    private void setupAPI() {
        String token=preferencesManager.getString(BaseActivity.AUTH);
        APIConfiguration.getInstance().setToken(token);
    }


    private void setupLocalStorage() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        LocalStorage.Configuration
                .getInstance()
                .setDatabase(Realm.getDefaultInstance());
    }

    private void setupUncaughtException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                FirebaseCrash.report(e);
                e.printStackTrace();
                //System.exit(0);
            }
        });
    }

    public PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }

    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }

    private void setupPreferences() {
        for (String key : REFERENCES_TO_RESET_ON_START) {
            preferencesManager.remove(key);
        }
    }


    @Override
    public boolean isConnected() {
        boolean haveConnection = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            haveConnection = activeNetwork.isConnectedOrConnecting();
        }
        return haveConnection;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ConnectionState.getInstance().onTerminate();
        preferencesManager.onDestroy();
    }


}
