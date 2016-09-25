package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.SecondStepTeamController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.ISecondStepTeamView;

public class SecondStepTeamActivity extends BaseActivity implements ISecondStepTeamView {

    private SecondStepTeamController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_team);
        setupToolbar();
        bindActivity(this);
        setSubtitle(R.id.add_subject);
        Bundle extra=getIntent().getExtras();

        ctrl=new SecondStepTeamController(this, extra.getIntArray(NavigationManager.ARGS+0));




    }


}
