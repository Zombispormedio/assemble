package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.views.ISecondStepTeamView;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class SecondStepTeamController extends Controller {

    private ISecondStepTeamView ctx;

    private FriendResource friendResource;

    private TeamResource teamResource;

    private int[] friendIds;

    public SecondStepTeamController(ISecondStepTeamView ctx, int[] friendIds) {
        super(ctx);
        this.ctx = ctx;
        this.friendIds=friendIds;
        friendResource=getResourceComponent().provideFriendResource();
        teamResource=getResourceComponent().provideTeamResource();

    }

    @Override
    public void onCreate() {
        bindParticipants();
    }

    private void bindParticipants() {
        ctx.setParticipantsTitle(friendIds.length, friendResource.countAll());
        ctx.bindParticipants(friendResource.getFriendInArrayofIds(friendIds));
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }
}
