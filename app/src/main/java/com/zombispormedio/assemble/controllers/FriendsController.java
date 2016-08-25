package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IFriendsView;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public class FriendsController extends AbstractController {

    private IFriendsView ctx;

    public FriendsController(IFriendsView ctx) {
        this.ctx = ctx;
    }

    public void onNewFriend() {
        ctx.goToNewFriend();
    }
}
