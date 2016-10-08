package com.zombispormedio.assemble.services;

import com.google.firebase.iid.FirebaseInstanceIdService;

import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 08/10/2016.
 */

public class InstanceIDService extends FirebaseInstanceIdService {

    private IApplicationView ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx=(IApplicationView)getApplication();
    }


    @Override
    public void onTokenRefresh() {

    }
}
