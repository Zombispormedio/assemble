package com.zombispormedio.assemble.services.events;

import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.models.Message;

import org.json.JSONObject;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface INotificationEventController {

    void init(ArrayList<JSONObject> data);

    Class<? extends BaseActivity> getIntentClass();

    Intent modifyIntent(Intent intent, boolean isApplicationActive);

    boolean permitIntent();

}
