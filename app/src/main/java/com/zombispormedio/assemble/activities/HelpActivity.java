package com.zombispormedio.assemble.activities;

import android.os.Bundle;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.HelpController;
import com.zombispormedio.assemble.views.IHelpView;

public class HelpActivity extends BaseActivity implements IHelpView {

    private HelpController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupToolbar();

        ctrl = new HelpController(this);
    }

}
