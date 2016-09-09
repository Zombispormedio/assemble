package com.zombispormedio.assemble.activities;

import android.os.Bundle;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateChatController;
import com.zombispormedio.assemble.views.ICreateChatView;

public class CreateChatActivity extends BaseActivity implements ICreateChatView {

    private CreateChatController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);
        setupToolbar();
        bindActivity(this);

        ctrl=new CreateChatController(this);
    }

}
