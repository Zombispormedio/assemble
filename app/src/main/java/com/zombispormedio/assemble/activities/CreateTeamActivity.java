package com.zombispormedio.assemble.activities;

import android.os.Bundle;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateTeamController;
import com.zombispormedio.assemble.views.ICreateTeamView;

public class CreateTeamActivity extends BaseActivity implements ICreateTeamView {

    private CreateTeamController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        setupToolbar();
        bindActivity(this);

        ctrl=new CreateTeamController(this);
    }

}
