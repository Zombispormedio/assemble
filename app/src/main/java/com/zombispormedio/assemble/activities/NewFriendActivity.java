package com.zombispormedio.assemble.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.NewFriendsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.NewFriendController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.INewFriendView;

import java.util.ArrayList;

import butterknife.BindView;

public class NewFriendActivity extends BaseActivity implements INewFriendView {

    @BindView(R.id.search_view)
    EditText searchView;

    @BindView(R.id.new_friends_list)
    RecyclerView friendsList;

    private NewFriendController ctrl;

    private NewFriendsRecyclerViewAdapter.Factory friendsListFactory;

    private NewFriendsRecyclerViewAdapter friendsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        setupToolbar();
        bindActivity(this);

        ctrl=new NewFriendController(this);

        setupList();

        ctrl.onCreate();

    }

    private void setupList() {
        friendsListFactory=new NewFriendsRecyclerViewAdapter.Factory();

        AndroidUtils.createListConfiguration(this, friendsList)
                .divider(true)
                .itemAnimation(true)
                .configure();
        friendsListFactory.setOnClickListener(ctrl.getOnClickOneFriend());

        friendsListAdapter=friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);
    }

    @Override
    public void bindSearchResults(ArrayList<FriendProfile> results) {
        friendsListAdapter.setData(results);
        friendsListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
