package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IFirstStepTeamView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class FirstStepTeamController extends Controller {

    @Nullable
    private IFirstStepTeamView ctx;

    private final FriendResource friendResource;

    @NonNull
    private final LinkedList<Integer> memberIds;

    public FirstStepTeamController(IFirstStepTeamView ctx) {
        super(ctx);
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
        memberIds = new LinkedList<>();
    }

    @Override
    public void onCreate() {
        renderFriends();
    }

    private void renderFriends() {
        ArrayList<FriendProfile> friends = friendResource.getAll();
        ctx.bindFriends(friends);
    }

    public void onFriendAddedToMembers(int position, @NonNull FriendProfile data) {
        ctx.addMember(data, position);
        memberIds.add(Integer.valueOf(data.id));
        ctx.setParticipantsSubtitle(memberIds.size(), ctx.getFriendsSize());
    }

    public void onMemberRemoved(int friendIndex, @NonNull FriendProfile data) {
        ctx.removeMember(friendIndex);
        memberIds.remove(Integer.valueOf(data.id));
        ctx.setParticipantsSubtitle(memberIds.size(), ctx.getFriendsSize());
    }

    public void onNext() {
        if (memberIds.size() > 1) {
            ctx.goToNextStep(Utils.toArray(memberIds));
        } else {
            ctx.showNeedMembers();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
