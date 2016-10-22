package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.holders.IFriendHolder;
import com.zombispormedio.assemble.views.fragments.IFriendsListView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendsListController extends Controller {

    private IFriendsListView ctx;

    private final FriendResource friendResource;

    private final FriendSubscription friendSubscription;

    private final FriendSubscriber friendSubscriber;

    private boolean refreshing;

    public FriendsListController(IFriendsListView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
        friendSubscription = getResourceComponent().provideFriendSubscription();
        friendSubscriber = new FriendSubscriber();
        friendSubscription.addSubscriber(friendSubscriber);
        refreshing = false;
    }

    @Override
    public void onCreate() {
        renderFriends();
    }

    private void renderFriends() {
        ArrayList<FriendProfile> friends = friendResource.getAll();
        ctx.bindFriends(friends);
    }

    public void onClickFriend(int position, FriendProfile data) {
    }

    public void onRefresh() {
        refreshing = true;
        friendSubscription.load();
    }

    public void onRemoveFriend(int position, FriendProfile data, final IFriendHolder holder) {
        holder.showProgress();
        friendResource.deleteFriend(data.id, new ServiceHandler<ArrayList<FriendProfile>, Error>(){
            @Override
            public void onError(Error error) {
                holder.hideProgress();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                holder.hideProgress();
                renderFriends();
                ctx.showRemovedFriend();
            }
        });
    }

    private class FriendSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderFriends();
            finishRefresh();
        }

        @Override
        public void notifyFail() {
            finishRefresh();
        }
    }

    private void finishRefresh() {
        if (refreshing) {
            refreshing = false;
            ctx.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
        friendSubscription.removeSubscriber(friendSubscriber);
    }


}
