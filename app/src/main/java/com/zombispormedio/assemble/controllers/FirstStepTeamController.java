package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.views.IFirstStepTeamView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class FirstStepTeamController extends Controller {

    private IFirstStepTeamView ctx;

    private FriendResource friendResource;

    public FirstStepTeamController(IFirstStepTeamView ctx) {
        super(ctx);
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
    }

    @Override
    public void onCreate() {
        bindFriends();
    }

    private void bindFriends() {
        ArrayList<FriendProfile> friends = friendResource.getAll();
        ctx.bindFriends(friends);
    }
}
