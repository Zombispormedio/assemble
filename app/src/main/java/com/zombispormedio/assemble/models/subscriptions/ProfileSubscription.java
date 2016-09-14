package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.factories.LoaderFactory;
import com.zombispormedio.assemble.models.loaders.ProfileLoader;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ProfileSubscription extends DataSubscription{

    private ProfileLoader loader;

    public ProfileSubscription() {
        loader= LoaderFactory.createProfileLoader();
    }

    @Override
    public void load(){
        loader.retrieve(new ISuccessHandler() {
            @Override
            public void onSuccess() {
                notifySubscribers();
            }
        });
    }


}
