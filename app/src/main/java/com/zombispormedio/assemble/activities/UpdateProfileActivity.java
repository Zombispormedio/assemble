package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.UpdateProfileController;
import com.zombispormedio.assemble.views.IUpdateProfileView;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class UpdateProfileActivity extends BaseActivity implements IUpdateProfileView {

    private UpdateProfileController ctrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Toolbar bar = (Toolbar) findViewById(R.id.update_profile_bar);
        setSupportActionBar(bar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        ctrl= new UpdateProfileController(this);
    }
}
