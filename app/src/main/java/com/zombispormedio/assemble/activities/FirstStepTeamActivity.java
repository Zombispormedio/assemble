package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.SelectedMemberHolder;
import com.zombispormedio.assemble.adapters.TeamFriendHolder;
import com.zombispormedio.assemble.adapters.lists.SelectedMembersListAdapter;
import com.zombispormedio.assemble.adapters.lists.TeamFriendsListAdapter;
import com.zombispormedio.assemble.controllers.FirstStepTeamController;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IFirstStepTeamView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstStepTeamActivity extends BaseActivity implements IFirstStepTeamView {

    private FirstStepTeamController ctrl;

    @BindView(R.id.members_list)
    RecyclerView membersList;

    @BindView(R.id.friends_list)
    RecyclerView friendsList;


    private TeamFriendsListAdapter.Factory friendsListFactory;

    private TeamFriendsListAdapter friendsListAdapter;

    private SelectedMembersListAdapter membersListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_team);
        setupToolbar();
        bindActivity(this);
        setDefaultSubtitle();

        ctrl=new FirstStepTeamController(this);

        setupFriends();

        setupMembers();


        ctrl.onCreate();
    }



    private void setupFriends() {
        friendsListFactory= new TeamFriendsListAdapter.Factory();

        AndroidUtils.createListConfiguration(this, friendsList)
                .divider(true)
                .itemAnimation(true)
                .configure();

        friendsListFactory.setOnClickListener(
                new IOnClickItemListHandler<TeamFriendHolder.SelectedContainer>() {
                    @Override
                    public void onClick(int position, TeamFriendHolder.SelectedContainer data) {

                        if(!data.isSelected()){
                            ctrl.onFriendAddedToMembers(position, data.getContent());
                        }else{
                            ctrl.onMemberRemoved(position, data.getContent());
                        }

                    }
                });

        friendsListAdapter = friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);

    }

    private void setupMembers() {
        membersListAdapter= new SelectedMembersListAdapter();

        AndroidUtils.createListConfiguration(this, membersList)
                .orientation(LinearLayoutManager.HORIZONTAL)
                .divider(true)
                .itemAnimation(true)
                .configure();

        membersListAdapter.setOnClickListener(
                new IOnClickItemListHandler<SelectedMemberHolder.Container>() {
                    @Override
                    public void onClick(int position, SelectedMemberHolder.Container data) {
                        ctrl.onMemberRemoved(data.getFriendIndex(), data.getContent());
                    }
                });

        membersList.setAdapter(membersListAdapter);

    }

    @OnClick(R.id.fab)
    public void onNextPage(View view){

    }


    @Override
    public void setParticipantsSubtitle(int number, int total) {
        String subtitle=String.format(getString(R.string.selected_participants), number,total);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setSubtitle(subtitle);
        }
    }

    @Override
    public void setDefaultSubtitle() {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setSubtitle(R.string.add_participants);
        }
    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> friends) {
        friendsListAdapter.setFriendProfiles(friends);
        membersListAdapter.clear();
    }

    @Override
    public void addMember(FriendProfile member, int friendIndex) {
        membersListAdapter.addMember(member, friendIndex);
        friendsListAdapter.selectFriend(friendIndex);
    }

    @Override
    public void removeMember(int friendIndex) {
        membersListAdapter.removeMemberByFriend(friendIndex);
        friendsListAdapter.deselectFriend(friendIndex);
    }

    @Override
    public int getFriendsSize() {
        return friendsListAdapter.getItemCount();
    }
}
