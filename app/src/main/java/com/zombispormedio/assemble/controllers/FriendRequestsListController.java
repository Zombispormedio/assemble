package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.views.IFriendRequestsListView;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendRequestsListController extends AbstractController{

    private IFriendRequestsListView ctx;
    private CurrentUser user;

    public FriendRequestsListController(IFriendRequestsListView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();
    }

    @Override
    public void onCreate() {
        ctx.bindFriendRequests(user.getFriendRequests());
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
}
