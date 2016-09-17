package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.INewFriendView;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendController extends AbstractController {

    private INewFriendView ctx;

    public NewFriendController(INewFriendView ctx) {
        this.ctx = ctx;
    }

    public IOnClickItemListHandler<FriendProfile> getOnClickOneFriend() {
        return  new IOnClickItemListHandler<FriendProfile>() {
            @Override
            public void onClick(int position, FriendProfile data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }
}
