package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestSubscription extends DataSubscription {

    private FriendRequestLoader loader;

    @Inject
    public FriendRequestSubscription(FriendRequestLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }
}
