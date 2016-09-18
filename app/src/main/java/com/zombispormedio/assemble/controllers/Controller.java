package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.models.subscriptions.DataSubscription;
import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class Controller extends AbstractController {
    private IApplicationView app;

    public Controller(IApplicationView app) {
        this.app = app;
    }

    protected ResourceComponent getResourceComponent(){
        return app!=null?app.getResourceComponent():null;
    }

    protected void loadAll(){
        ResourceComponent component=getResourceComponent();
        DataSubscription[] subscriptions=new DataSubscription[]{
                component.provideProfileSubscription(),
                component.provideFriendSubscription(),
                component.provideFriendRequestSubscription(),
                component.provideTeamSubscription(),
                component.provideMeetingSubscription(),
                component.provideChatSubscription()
        };

        for (DataSubscription subscription: subscriptions) {
            subscription.load();
        }
    }
}
