package com.zombispormedio.assemble.fragments;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.lists.FriendsListAdapter;
import com.zombispormedio.assemble.controllers.FriendsListController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.fragments.IFriendsListView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;

public class FriendsListFragment extends BaseFragment implements IFriendsListView {

    private FriendsActivity view;

    @Nullable
    @BindView(R.id.friends_list)
    RecyclerView friendsList;

    @Nullable
    @BindView(R.id.friends_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private FriendsListController ctrl;

    private FriendsListAdapter friendsListAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (FriendsActivity) getActivity();
        bindView(this, view);
        ctrl = new FriendsListController(this);

        setupFriends();

        setupRefresh();

        ctrl.onCreate();
    }

    private void setupFriends() {
        FriendsListAdapter.Factory friendsListFactory = new FriendsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, friendsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        friendsListFactory.setOnClickListener((position, data) -> ctrl.onClickFriend(position, data));

        friendsListFactory.setRemoveButtonListener((position, data, holder) -> ctrl.onRemoveFriend(position, data, holder));
        friendsListAdapter = friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> ctrl.onRefresh());
    }

    @Override
    public void bindFriends(@NonNull ArrayList<FriendProfile> data) {
        friendsListAdapter.addAll(data);
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRemovedFriend() {
        showAlert(view.getString(R.string.removed_friend));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
