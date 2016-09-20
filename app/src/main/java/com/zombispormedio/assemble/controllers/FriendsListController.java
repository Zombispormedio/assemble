package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.views.IFriendHolder;
import com.zombispormedio.assemble.views.IFriendsListView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendsListController extends Controller {

    private IFriendsListView ctx;

    private FriendResource friendResource;

    private FriendSubscription friendSubscription;

    private FriendSubscriber friendSubscriber;

    private boolean refreshing;

    public FriendsListController(IFriendsListView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
        friendSubscription = getResourceComponent().provideFriendSubscription();
        friendSubscriber = new FriendSubscriber();
        friendSubscription.addSubscriber(friendSubscriber);
        refreshing=false;
    }

    @Override
    public void onCreate() {
        bindFriends();
    }

    private void bindFriends() {
        ArrayList<FriendProfile> friends = friendResource.getAll();

        if (friends.size() > 0) {
            ctx.bindFriends(friends);
        }
    }

    public void onClickFriend(int position, FriendProfile data) {
    }

    public void onRefresh() {
        refreshing=true;
        friendSubscription.load();
    }

    public void onRemoveFriend(int position, FriendProfile data, IFriendHolder holder) {

    }

    private class FriendSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindFriends();
            finishRefresh();
        }
    }

    private void finishRefresh(){
        if(refreshing){
            refreshing=false;
            ctx.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
        friendSubscription.removeSubscriber(friendSubscriber);
    }


}
