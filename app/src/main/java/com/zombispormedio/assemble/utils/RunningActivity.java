package com.zombispormedio.assemble.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 14/10/2016.
 */

public class RunningActivity implements Application.ActivityLifecycleCallbacks {

    public static String whoIsRunning = "";

    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        whoIsRunning = activity.getClass().getName();
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
