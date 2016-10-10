package com.zombispormedio.assemble.models.subscriptions;


import com.zombispormedio.assemble.models.loaders.FriendLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendSubscription extends DataSubscription{

    private FriendLoader loader;

    @Inject
    public FriendSubscription(FriendLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }
}
