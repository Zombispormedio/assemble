package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.factories.LoaderFactory;
import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestSubscription extends Subscription implements IDataSubscription {

    private FriendRequestLoader loader;

    public FriendRequestSubscription() {
        this.loader = LoaderFactory.createFriendRequestLoader();
    }

    @Override
    public void load() {
        loader.retrieve(new ISuccessHandler() {
            @Override
            public void onSuccess() {
                notifySubscribers();
            }
        });
    }
}
