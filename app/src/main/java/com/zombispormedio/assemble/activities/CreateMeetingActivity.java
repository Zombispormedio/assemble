package com.zombispormedio.assemble.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateMeetingController;
import com.zombispormedio.assemble.fragments.TeamDialogFragment;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.views.ICreateMeetingView;

import butterknife.OnClick;

public class CreateMeetingActivity extends BaseActivity implements ICreateMeetingView {

    private CreateMeetingController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        setupToolbar();
        setHomeUpIcon(R.drawable.close_home);
        bindActivity(this);

        ctrl=new CreateMeetingController(this);


    }

    @OnClick(R.id.save_button)
    public void onSave(){
        FragmentManager fm= getSupportFragmentManager();

        TeamDialogFragment teamDialogFragment= new TeamDialogFragment();

        teamDialogFragment.setOnItemClickedListener(new IOnClickItemListHandler<Team>() {
            @Override
            public void onClick(int position, Team data) {
                Logger.d(position);
                Logger.d(data);
            }
        });

        teamDialogFragment.show(fm, "fragment_team_dialog");

    }



}
