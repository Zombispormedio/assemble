package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IFirstStepTeamView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class FirstStepTeamController extends Controller {

    private IFirstStepTeamView ctx;

    private FriendResource friendResource;

    private LinkedList<Integer> memberIds;

    public FirstStepTeamController(IFirstStepTeamView ctx) {
        super(ctx);
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
        memberIds=new LinkedList<>();
    }

    @Override
    public void onCreate() {
        bindFriends();
    }

    private void bindFriends() {
        ArrayList<FriendProfile> friends = friendResource.getAll();
        ctx.bindFriends(friends);
    }

    public void onFriendAddedToMembers(int position, FriendProfile data) {
        ctx.addMember(data, position);
        memberIds.add(Integer.valueOf(data.id));
        ctx.setParticipantsSubtitle(memberIds.size(), ctx.getFriendsSize());
    }

    public void onMemberRemoved(int friendIndex, FriendProfile data) {
        ctx.removeMember(friendIndex);
        memberIds.remove(Integer.valueOf(data.id));
        ctx.setParticipantsSubtitle(memberIds.size(), ctx.getFriendsSize());
    }

    public void onNext() {
        if(memberIds.size()>1){
            ctx.goToNextStep(Utils.toArray(memberIds));
        }else{
            ctx.showNeedMembers();
        }
    }
}
