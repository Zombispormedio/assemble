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

    private String imagePath;

    public SecondStepTeamController(ISecondStepTeamView ctx, int[] friendIds) {
        super(ctx);
        this.ctx = ctx;
        this.friendIds=friendIds;
        friendResource=getResourceComponent().provideFriendResource();
        teamResource=getResourceComponent().provideTeamResource();
        imagePath=null;
    }

    @Override
    public void onCreate() {
        bindParticipants();
    }

    private void bindParticipants() {
        ctx.setParticipantsTitle(friendIds.length, friendResource.countAll());
        ctx.bindParticipants(friendResource.getFriendInArrayofIds(friendIds));
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    public void onCreateTeam() {
        String name=ctx.getName();

        if(!name.isEmpty()){
            beginCreatingTeam();
        }else{
            ctx.showNameEmpty();
        }
    }

    private void beginCreatingTeam() {

    }
}
