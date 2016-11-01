package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.models.loaders.MeetingLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class MeetingSubscription extends DataSubscription {

    private final MeetingLoader loader;

    @Inject
    public MeetingSubscription(MeetingLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }
}
