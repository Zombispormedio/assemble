package com.zombispormedio.assemble.activities;

import android.os.Bundle;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateMeetingController;
import com.zombispormedio.assemble.views.ICreateMeetingView;

public class CreateMeetingActivity extends BaseActivity implements ICreateMeetingView {

    private CreateMeetingController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        setupToolbar();
        bindActivity(this);

        ctrl=new CreateMeetingController(this);
    }

}
