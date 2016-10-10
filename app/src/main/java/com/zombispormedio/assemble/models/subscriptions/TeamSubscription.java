package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.models.loaders.TeamLoader;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class TeamSubscription extends DataSubscription {

    private TeamLoader loader;

    @Inject
    public TeamSubscription(TeamLoader loader) {
        this.loader = loader;
    }

    @Override
    public void load() {
        loader.retrieve(deferSubscribers());
    }
}
