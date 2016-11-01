package com.zombispormedio.assemble;


import com.onesignal.OneSignal;
import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.models.components.DaggerResourceComponent;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.models.modules.SubscriptionModule;
import com.zombispormedio.assemble.models.services.api.APIConfiguration;
import com.zombispormedio.assemble.net.ConnectionState;
import com.zombispormedio.assemble.services.NotificationOpenedEvent;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.utils.RunningActivity;
import com.zombispormedio.assemble.views.IApplicationView;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import net.danlew.android.joda.JodaTimeAndroid;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.AUTH;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.LOADED;


/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class AssembleApplication extends Application implements IApplicationView {


    private final static String[] PREFERENCES_TO_RESET_ON_START = new String[]{
            LOADED,
            CHAT_ID
    };

    private ResourceComponent resourceComponent;

    private PreferencesManager preferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();

        configureOneSignal();

        JodaTimeAndroid.init(this);

        ConnectionState.getInstance().setContext(this);

        registerActivityLifecycleCallbacks(new RunningActivity());

        preferencesManager = new PreferencesManager(this);

        resourceComponent = DaggerResourceComponent.builder()
                .resourceModule(new ResourceModule())
                .subscriptionModule(new SubscriptionModule())
                .build();

        setupPreferences();

        setupLocalStorage();

        setupAPI();

    }

    private void configureOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.ERROR);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedEvent(this))
                .init();
    }


    private void setupAPI() {
        String token = preferencesManager.getString(AUTH);
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


    public PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }


    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }

    private void setupPreferences() {
        for (String key : PREFERENCES_TO_RESET_ON_START) {
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
    public void sendBroadcastByIntent(Intent intent) {
        sendBroadcast(intent);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ConnectionState.getInstance().onTerminate();
        preferencesManager.onDestroy();
        Realm.getDefaultInstance().close();

    }


    @NonNull
    @Override
    public String getAppString(int id) {
        return getString(id);
    }

    @Override
    public int getAppColor(int id) {
        return ContextCompat.getColor(this, id);
    }

    @Override
    public boolean isRunning(String running) {
        return RunningActivity.whoIsRunning.equals(running);
    }

    @Override
    public boolean isActive() {
        return !RunningActivity.whoIsRunning.isEmpty();
    }

    @NonNull
    @Override
    public Intent createIntent(Class<? extends BaseActivity> activityClass) {
        return new Intent(getApplicationContext(), activityClass);
    }

    @Override
    public void startIntent(@NonNull Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
