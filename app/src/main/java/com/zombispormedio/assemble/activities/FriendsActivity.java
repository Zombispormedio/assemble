package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.FriendRequestsRecyclerViewAdapter;
import com.zombispormedio.assemble.adapters.FriendsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.FriendsController;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IFriendsView;

import java.util.ArrayList;

public class FriendsActivity extends BaseActivity implements IFriendsView {

    private FriendsController ctrl;

    private RecyclerView _listFriends;

    private RecyclerView _listFriendRequests;

    private FriendsRecyclerViewAdapter.Factory _listFriendsFactory;

    private  FriendsRecyclerViewAdapter _listFriendsAdapter;

    private FriendRequestsRecyclerViewAdapter.Factory _listFriendRequestsFactory;

    private FriendRequestsRecyclerViewAdapter _listFriendRequestsAdapter;

    private TextView _progressLabel;

    private ProgressBar _progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setupToolbar();

        ctrl = new FriendsController(this);

        _listFriendsFactory = new FriendsRecyclerViewAdapter.Factory();
        _listFriendsAdapter=null;

        _listFriendRequestsFactory = new FriendRequestsRecyclerViewAdapter.Factory();
        _listFriendRequestsAdapter=null;

        _listFriends = (RecyclerView) findViewById(R.id.friends_list);
        _listFriendRequests = (RecyclerView) findViewById(R.id.req_friends_list);
        _progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        _progressLabel = (TextView) findViewById(R.id.loading_label);

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
       AndroidUtils.createListConfiguration(this, _listFriendRequests)
               .divider(true)
               .itemAnimation(true)
               .scrolling(false)
               .configure();
        _listFriendRequestsFactory.setOnClickListener(ctrl.getOnClickOneRequest());

    }

    private void setupFriends() {
        AndroidUtils.createListConfiguration(this, _listFriends)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        _listFriendsFactory.setOnClickListener(ctrl.getOnClickOneFriend());
    }


    public void bindFriends(ArrayList<FriendProfile> data) {
        if(_listFriendsAdapter==null){
            _listFriendsAdapter=_listFriendsFactory.make(data);
            _listFriends.setAdapter(_listFriendsAdapter);
        }else{
            _listFriendsAdapter.setData(data);
            _listFriendsAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void bindFriendRequests(ArrayList<FriendRequestProfile> data) {
        if(_listFriendRequestsAdapter==null){
            _listFriendRequestsAdapter=_listFriendRequestsFactory.make(data);
            _listFriendRequests.setAdapter(_listFriendRequestsAdapter);
        }else{
            _listFriendRequestsAdapter.setData(data);
            _listFriendRequestsAdapter.notifyDataSetChanged();
        }
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
    public void showFriendsList() {
        _listFriends.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFriendsList() {
        _listFriends.setVisibility(View.GONE);
    }

    @Override
    public void showRequestsList() {
        _listFriendRequests.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRequestsList() {
        _listFriendRequests.setVisibility(View.GONE);
    }


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }
}
