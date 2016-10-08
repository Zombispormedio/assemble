package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.models.loaders.MessageLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 08/10/2016.
 */

public class MessageSubscription extends DataSubscription {

    private MessageLoader loader;

    @Inject
    public MessageSubscription(MessageLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }
}
