package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendsActivity extends BaseActivity implements IFriendsView {

    @BindView(R.id.friends_list)
    RecyclerView _listFriends;

    @BindView(R.id.req_friends_list)
    RecyclerView _listFriendRequests;


    private FriendsController ctrl;

    private FriendsRecyclerViewAdapter.Factory _listFriendsFactory;

    private FriendsRecyclerViewAdapter _listFriendsAdapter;

    private FriendRequestsRecyclerViewAdapter.Factory _listFriendRequestsFactory;

    private FriendRequestsRecyclerViewAdapter _listFriendRequestsAdapter;

    @BindView(R.id.loading_label)
    TextView _progressLabel;

    @BindView(R.id.progress_bar)
    ProgressBar _progressBar;

    @BindView(R.id.no_more_friends_layout)
    FrameLayout noMoreFriendsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setupToolbar();
        bindActivity(this);

        ctrl = new FriendsController(this);

        setupFriends();

        setupRequestFriends();

        ctrl.onCreate();
    }

    @OnClick(R.id.fab)
    public void onNewFriendButtonClick(View view) {
        ctrl.onNewFriend();
    }

    private void setupRequestFriends() {
        _listFriendRequestsFactory = new FriendRequestsRecyclerViewAdapter.Factory();
        _listFriendRequestsAdapter = null;
        AndroidUtils.createListConfiguration(this, _listFriendRequests)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        _listFriendRequestsFactory.setOnClickListener(ctrl.getOnClickOneRequest());
    }

    private void setupFriends() {
        _listFriendsFactory = new FriendsRecyclerViewAdapter.Factory();
        _listFriendsAdapter = null;
        AndroidUtils.createListConfiguration(this, _listFriends)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        _listFriendsFactory.setOnClickListener(ctrl.getOnClickOneFriend());
    }


    public void bindFriends(ArrayList<FriendProfile> data) {
        if (_listFriendsAdapter == null) {
            _listFriendsAdapter = _listFriendsFactory.make(data);
            _listFriends.setAdapter(_listFriendsAdapter);
        } else {
            _listFriendsAdapter.setData(data);
            _listFriendsAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void bindFriendRequests(ArrayList<FriendRequestProfile> data) {
        if (_listFriendRequestsAdapter == null) {
            _listFriendRequestsAdapter = _listFriendRequestsFactory.make(data);
            _listFriendRequests.setAdapter(_listFriendRequestsAdapter);
        } else {
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
    public void showEndOfLists() {
        noMoreFriendsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEndOfLists() {
        noMoreFriendsLayout.setVisibility(View.GONE);
    }


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }
}
