package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.factories.LoaderFactory;
import com.zombispormedio.assemble.models.loaders.TeamLoader;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class TeamSubscription extends Subscription implements IDataSubscription {

    private TeamLoader loader;


    public TeamSubscription() {
        this.loader = LoaderFactory.createTeamLoader();
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
