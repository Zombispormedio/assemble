package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.views.activities.IChatView;

public class ChatActivity extends BaseActivity implements IChatView{

    private ChatController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupToolbar();
        bindActivity(this);

        ctrl=new ChatController(this);
    }

}
