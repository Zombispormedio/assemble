package com.zombispormedio.assemble.models.subscriptions;


import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.factories.LoaderFactory;
import com.zombispormedio.assemble.models.loaders.MeetingLoader;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class MeetingSubscription extends DataSubscription{

    private MeetingLoader loader;

    public MeetingSubscription() {
        this.loader = LoaderFactory.createMeetingLoader();
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
