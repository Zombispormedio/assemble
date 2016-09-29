package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateChatController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.ICreateChatView;

import java.util.ArrayList;

import butterknife.BindView;

public class CreateChatActivity extends BaseActivity implements ICreateChatView {

    private CreateChatController ctrl;

    @BindView(R.id.friends_list)
    RecyclerView friendsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);
        setupToolbar();
        bindActivity(this);

        ctrl=new CreateChatController(this);

        setupFriends();

        ctrl.onCreate();
    }

    private void setupFriends() {


    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> friends) {

    }
}
