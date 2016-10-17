package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.TeamFriendHolder;
import com.zombispormedio.assemble.adapters.SelectedMemberHolder;
import com.zombispormedio.assemble.adapters.lists.SelectedMembersListAdapter;
import com.zombispormedio.assemble.adapters.lists.TeamFriendsListAdapter;
import com.zombispormedio.assemble.controllers.FirstStepTeamController;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IFirstStepTeamView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstStepTeamActivity extends BaseActivity implements IFirstStepTeamView {

    private FirstStepTeamController ctrl;

    @BindView(R.id.members_list)
    RecyclerView membersList;

    @BindView(R.id.friends_list)
    RecyclerView friendsList;

    @BindView(R.id.divider)
    View divider;


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
        TeamFriendsListAdapter.Factory friendsListFactory = new TeamFriendsListAdapter.Factory();

        AndroidUtils.createListConfiguration(this, friendsList)
                .divider(true)
                .itemAnimation(true)
                .configure();

        friendsListFactory.setOnClickListener(
                (position, data) -> {
                    if(!data.isSelected()){
                        ctrl.onFriendAddedToMembers(position, data.getContent());
                    }else{
                        ctrl.onMemberRemoved(position, data.getContent());
                    }
                });

        friendsListAdapter = friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);

    }

    private void setupMembers() {
        membersListAdapter= new SelectedMembersListAdapter();

        AndroidUtils.createListConfiguration(this, membersList)
                .orientation(LinearLayoutManager.HORIZONTAL)
                .itemAnimation(true)
                .configure();

        membersListAdapter.setOnClickListener(
                (position, data) -> ctrl.onMemberRemoved(data.getFriendIndex(), data.getContent()));

        membersList.setAdapter(membersListAdapter);

    }

    @OnClick(R.id.fab)
    public void onNextPage(View view){
        ctrl.onNext();
    }


    @Override
    public void setParticipantsSubtitle(int number, int total) {
        String subtitle=String.format(getString(R.string.selected_participants), number,total);
        setSubtitle(subtitle);

    }

    @Override
    public void setDefaultSubtitle() {
       setSubtitle(R.string.add_participants);
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

        int itemCount=membersListAdapter.getItemCount();
        if(itemCount>0){
            if(divider.getVisibility()!=View.VISIBLE){
                divider.setVisibility(View.VISIBLE);
            }

            if(itemCount>3){
                membersList.scrollToPosition(itemCount-1);
            }

        }

    }

    @Override
    public void removeMember(int friendIndex) {
        membersListAdapter.removeMemberByFriend(friendIndex);
        friendsListAdapter.deselectFriend(friendIndex);

        if(membersListAdapter.getItemCount()==0){
            divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getFriendsSize() {
        return friendsListAdapter.getItemCount();
    }

    @Override
    public void showNeedMembers() {
        showAlert(getString(R.string.need_more_than_one_members));
    }

    @Override
    public void goToNextStep(int[] memberIds) {
        NavigationManager.SecondStepCreateTeam(this, memberIds);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
