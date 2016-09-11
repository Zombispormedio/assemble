package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
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




    private FriendsController ctrl;


    @BindView(R.id.loading_label)
    TextView _progressLabel;

    @BindView(R.id.progress_bar)
    ProgressBar _progressBar;
    
    @BindView(R.id.friends_pager)
    ViewPager friendsPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setupToolbar();
        bindActivity(this);

        ctrl = new FriendsController(this);

        setupPager();
        
        ctrl.onCreate();
    }

    private void setupPager() {
    }

    @OnClick(R.id.fab)
    public void onNewFriendButtonClick(View view) {
        ctrl.onNewFriend();
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
    public void showLists() {
        friendsPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLists() {
        friendsPager.setVisibility(View.GONE);
    }
    


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }
}
