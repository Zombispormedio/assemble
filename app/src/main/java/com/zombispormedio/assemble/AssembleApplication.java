package com.zombispormedio.assemble;


import com.google.firebase.crash.FirebaseCrash;

import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.components.DaggerResourceComponent;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.utils.PreferencesManager;

import android.app.Application;
import android.util.Log;


/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class AssembleApplication extends Application {

    private ResourceComponent resourceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupUncaughtException();
        this.resourceComponent= DaggerResourceComponent.builder().resourceModule(new ResourceModule()).build();
        PreferencesManager preferencesManager = new PreferencesManager(this);
        preferencesManager.remove(HomeActivity.LOADED);

    }

    private void setupUncaughtException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                FirebaseCrash.report(e);
                e.printStackTrace();
                System.exit(0);
            }
        });
    }


    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }


}
