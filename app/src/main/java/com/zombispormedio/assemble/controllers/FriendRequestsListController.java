package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.IFriendRequestHolder;
import com.zombispormedio.assemble.views.IFriendRequestsListView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendRequestsListController extends Controller {

    private IFriendRequestsListView ctx;

    private FriendRequestResource friendRequestResource;

    private FriendRequestSubscription friendRequestSubscription;

    private FriendRequestSubscriber friendRequestSubscriber;

    private boolean refreshing;


    public FriendRequestsListController(IFriendRequestsListView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;

        friendRequestResource = getResourceComponent().provideFriendRequestResource();
        friendRequestSubscription = getResourceComponent().provideFriendRequestSubscription();
        friendRequestSubscriber = new FriendRequestSubscriber();
        friendRequestSubscription.addSubscriber(friendRequestSubscriber);

        friendRequestResource.setFriendRequestSubscription(friendRequestSubscription);

        refreshing=false;
    }

    @Override
    public void onCreate() {
        bindRequests();
    }

    private void bindRequests() {
        ArrayList<FriendRequestProfile> friendRequests = friendRequestResource.getAll();

        if (friendRequests.size() > 0) {
            ctx.bindFriendRequests(friendRequests);
        }
    }

    public void  onClickRequestItem(int position, FriendRequestProfile data) {

    }

    public void onAcceptRequest(int position, FriendRequestProfile data, final IFriendRequestHolder holder) {
        holder.showProgress();
        friendRequestResource.acceptRequest(data.id, new ServiceHandler<ArrayList<FriendRequestProfile>, Error>(){
            @Override
            public void onError(Error error) {
                holder.hideProgress();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<FriendRequestProfile> result) {
                holder.hideProgress();
                bindRequests();
                ctx.showFriendAccepted();
            }
        });
    }

    public void onRejectRequest(int position, FriendRequestProfile data, final IFriendRequestHolder holder) {
        holder.showProgress();
        friendRequestResource.rejectRequest(data.id, new ServiceHandler<ArrayList<FriendRequestProfile>, Error>(){
            @Override
            public void onError(Error error) {
                holder.hideProgress();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<FriendRequestProfile> result) {
                holder.hideProgress();
                bindRequests();
                ctx.showFriendAccepted();
            }
        });
    }

    public void onRefresh() {
        refreshing=true;
        friendRequestSubscription.load();
    }

    private class FriendRequestSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindRequests();
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
        friendRequestSubscription.removeSubscriber(friendRequestSubscriber);
    }
}
