package com.zombispormedio.assemble.models.subscriptions;
import com.zombispormedio.assemble.models.loaders.ChatLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ChatSubscription extends DataSubscription {

    private final ChatLoader loader;

    @Inject
    public ChatSubscription(ChatLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }


}
