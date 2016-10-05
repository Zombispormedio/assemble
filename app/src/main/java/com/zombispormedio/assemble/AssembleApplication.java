package com.zombispormedio.assemble;

import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.models.components.DaggerResourceComponent;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.modules.ResourceModule;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class AssembleApplication extends Application {

    private ResourceComponent resourceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.resourceComponent= DaggerResourceComponent.builder().resourceModule(new ResourceModule()).build();
    }

    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }

}
