package com.zombispormedio.assemble.services;

import com.google.firebase.iid.FirebaseInstanceIdService;

import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 08/10/2016.
 */

public class InstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onTokenRefresh() {

    }
}
