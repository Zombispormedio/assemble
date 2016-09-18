package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
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

    public FriendsListController(IFriendsListView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
        friendSubscription = CurrentUser.getInstance().getFriendSubscription();
        friendSubscriber = new FriendSubscriber();
        friendSubscription.addSubscriber(friendSubscriber);
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

    public IOnClickItemListHandler<FriendProfile> getOnClickOneFriend() {
        return new IOnClickItemListHandler<FriendProfile>() {
            @Override
            public void onClick(int position, FriendProfile data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }

    private class FriendSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindFriends();
        }
    }

    @Override
    public void onDestroy() {
        ctx = null;
        friendSubscription.removeSubscriber(friendSubscriber);
    }
}
