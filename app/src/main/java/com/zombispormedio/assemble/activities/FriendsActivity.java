package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.FriendsAdapter;
import com.zombispormedio.assemble.controllers.FriendsController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.DividerItemDecoration;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IFriendsView;

import java.util.ArrayList;

public class FriendsActivity extends BaseActivity implements IFriendsView{

    private FriendsController ctrl;

    private RecyclerView _listFriends;
    private RecyclerView _listRequestFriends;


    private TextView _progressLabel;
    private ProgressBar _progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        setupToolbar();

        ctrl= new FriendsController(this);

        _listFriends =(RecyclerView) findViewById(R.id.friends_list);
        _listRequestFriends =(RecyclerView) findViewById(R.id.req_friends_list);
        _progressBar =(ProgressBar)findViewById(R.id.progress_bar);
        _progressLabel =(TextView)findViewById(R.id.loading_label);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.onNewFriend();
            }
        });

        setupFriends();

        setupRequestFriends();

        ctrl.onCreate();
    }

    private void setupRequestFriends() {
        AndroidUtils.setupDefaultList(this, _listRequestFriends);
    }

    private void setupFriends() {
        AndroidUtils.setupDefaultList(this, _listFriends);
    }



    public void bindFriends(ArrayList<FriendProfile> data){
        _listFriends.setAdapter(new FriendsAdapter(data));
    }

    @Override
    public void bindRequestFriends(ArrayList<FriendProfile> data) {
        _listRequestFriends.setAdapter(new FriendsAdapter(data));
    }

    @Override
    public void loading() {
        _progressBar.setVisibility(View.VISIBLE);
        _progressLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void unloading() {
        _progressBar.setVisibility(View.GONE);
        _progressLabel.setVisibility(View.GONE);
    }


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }
}
