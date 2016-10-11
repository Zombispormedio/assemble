package com.zombispormedio.assemble.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 08/10/2016.
 */

public class MessagingIDService extends FirebaseInstanceIdService {

    public static final String MESSAGING_ID="messaging:id";

    private PreferencesManager preferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        preferencesManager = new PreferencesManager(this);
    }


    @Override
    public void onTokenRefresh() {
        String refreshedToken=FirebaseInstanceId.getInstance().getToken();
        boolean isNotAuth=preferencesManager.getString(BaseActivity.AUTH).isEmpty();

        if(isNotAuth){
            preferencesManager.set(MESSAGING_ID, refreshedToken);
        }else{
            sendRegistrationToServer(refreshedToken);
        }
    }

    private void sendRegistrationToServer(final String refreshedToken) {
        UserResource resource=getResourceComponent().provideUserResource();

        resource.refreshGCM(refreshedToken, new ServiceHandler<Result, Error>(){
            @Override
            public void onError(Error error) {
                Logger.d(error.msg);
            }

            @Override
            public void onSuccess(Result result) {
                Logger.d(result.msg);
            }
        });

    }


    protected ResourceComponent getResourceComponent(){
        return ((AssembleApplication)getApplication()).getResourceComponent();
    }
}
