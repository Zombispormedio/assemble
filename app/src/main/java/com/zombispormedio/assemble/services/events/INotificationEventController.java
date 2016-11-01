package com.zombispormedio.assemble.services.events;

import com.zombispormedio.assemble.activities.BaseActivity;

import org.json.JSONObject;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface INotificationEventController {

    void init(ArrayList<JSONObject> data);

    @NonNull
    Class<? extends BaseActivity> getIntentClass();

    @NonNull
    Intent modifyIntent(Intent intent, boolean isApplicationActive);

    boolean permitIntent();

}
