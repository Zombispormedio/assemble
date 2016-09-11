package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.views.IFriendsListView;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendsListController extends  AbstractController {

    private IFriendsListView ctx;
    private CurrentUser user;

    public FriendsListController(IFriendsListView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();
    }

    @Override
    public void onCreate() {
        ctx.bindFriends(user.getFriends());
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
}
