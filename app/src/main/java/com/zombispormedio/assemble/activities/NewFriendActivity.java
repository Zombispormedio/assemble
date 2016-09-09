package com.zombispormedio.assemble.activities;

import android.os.Bundle;


import com.zombispormedio.assemble.R;

public class NewFriendActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        setupToolbar();
        bindActivity(this);
    }

}
