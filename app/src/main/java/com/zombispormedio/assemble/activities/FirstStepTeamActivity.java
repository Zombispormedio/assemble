package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.FirstStepTeamController;
import com.zombispormedio.assemble.models.FriendProfile;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_team);
        setupToolbar();
        bindActivity(this);

        ctrl=new FirstStepTeamController(this);

        getSupportActionBar().setSubtitle(R.string.add_participants);
    }

    @OnClick(R.id.fab)
    public void onNextPage(View view){

    }


    @Override
    public void setParticipantsSubtitle(int number, int total) {

    }

    @Override
    public void setDefaultSubtitle() {

    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> friends) {

    }

    @Override
    public void addMember(FriendProfile member) {

    }

    @Override
    public void removeMember(int position) {

    }
}
