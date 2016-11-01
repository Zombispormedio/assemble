package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.pagers.FriendsPagerAdapter;
import com.zombispormedio.assemble.controllers.FriendsController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IFriendsView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class FriendsActivity extends BaseActivity implements IFriendsView {

    private FriendsController ctrl;

    @BindView(R.id.loading_label)
    TextView _progressLabel;

    @BindView(R.id.progress_bar)
    ProgressBar _progressBar;

    @BindView(R.id.friends_pager)
    ViewPager friendsPager;

    @BindView(R.id.friends_button_tab)
    Button friendsTab;

    @BindView(R.id.friend_requests_button_tab)
    Button friendRequestsTab;


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
        FriendsPagerAdapter adapter = new FriendsPagerAdapter(getSupportFragmentManager(), 2);
        friendsPager.setAdapter(adapter);
        friendsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        onFriendsTab();
                        break;
                    case 1:
                        onFriendRequestsTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        goToFriendsList();
        onFriendsTab();
    }

    @OnClick(R.id.fab)
    public void onNewFriendButtonClick(View view) {
        ctrl.onNewFriend();
    }

    @OnClick(R.id.friends_button_tab)
    public void onFriendsTab(View view) {
        goToFriendsList();
    }

    @OnClick(R.id.friend_requests_button_tab)
    public void onFriendRequestsTab(View view) {
        goToFriendRequestsList();
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
    public void goToFriendsList() {
        friendsPager.setCurrentItem(0);

    }

    @Override
    public void goToFriendRequestsList() {
        friendsPager.setCurrentItem(1);
    }

    @Override
    public void onFriendsTab() {
        friendsTab.setEnabled(false);
        friendRequestsTab.setEnabled(true);
        ctrl.onFriendTabEnter();
    }


    @Override
    public void onFriendRequestsTab() {
        friendRequestsTab.setEnabled(false);
        friendsTab.setEnabled(true);
        ctrl.onFriendRequestTab();
    }


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
