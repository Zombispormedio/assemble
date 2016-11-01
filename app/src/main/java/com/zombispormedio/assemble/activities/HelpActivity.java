package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.HelpController;
import com.zombispormedio.assemble.views.activities.IHelpView;

import android.os.Bundle;

public class HelpActivity extends BaseActivity implements IHelpView {

    private HelpController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupToolbar();
        bindActivity(this);

        ctrl = new HelpController(this);
    }

}
