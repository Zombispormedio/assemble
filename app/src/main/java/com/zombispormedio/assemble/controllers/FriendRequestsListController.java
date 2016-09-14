package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.views.IFriendRequestsListView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendRequestsListController extends AbstractController{

    private IFriendRequestsListView ctx;
    private FriendRequestResource friendRequestResource;
    private FriendRequestSubscription friendRequestSubscription;
    private FriendRequestSubscriber friendRequestSubscriber;

    public FriendRequestsListController(IFriendRequestsListView ctx) {
        this.ctx = ctx;

        friendRequestResource= ResourceFactory.createFriendRequestResource();
        friendRequestSubscription=CurrentUser.getInstance().getFriendRequestSubscription();
        friendRequestSubscriber=new FriendRequestSubscriber();
        friendRequestSubscription.addSubscriber(friendRequestSubscriber);
    }

    @Override
    public void onCreate() {
        bindRequests();
    }

    private void bindRequests(){
        ArrayList<FriendRequestProfile> friendRequests = friendRequestResource.getAll();

        if (friendRequests.size() > 0) {
            ctx.bindFriendRequests(friendRequests);
        }
    }

    public IOnClickItemListHandler<FriendRequestProfile> getOnClickOneRequest() {
        return new IOnClickItemListHandler<FriendRequestProfile>() {
            @Override
            public void onClick(int position, FriendRequestProfile data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }

    private class FriendRequestSubscriber extends Subscriber{

        @Override
        public void notifyChange() {
            bindRequests();
        }
    }

    @Override
    public void onDestroy() {
        ctx=null;
        friendRequestSubscription.removeSubscriber(friendRequestSubscriber);
    }
}
