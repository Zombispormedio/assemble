package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.factories.LoaderFactory;
import com.zombispormedio.assemble.models.loaders.ChatLoader;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ChatSubscription extends Subscription implements IDataSubscription {

    private ChatLoader loader;

    public ChatSubscription() {
        this.loader = LoaderFactory.createChatLoader();
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
