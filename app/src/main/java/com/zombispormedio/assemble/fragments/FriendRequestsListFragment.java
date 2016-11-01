package com.zombispormedio.assemble.fragments;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.lists.FriendRequestsListAdapter;
import com.zombispormedio.assemble.controllers.FriendRequestsListController;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.fragments.IFriendRequestsListView;

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


public class FriendRequestsListFragment extends BaseFragment implements IFriendRequestsListView {

    private FriendsActivity view;

    @Nullable
    @BindView(R.id.req_friends_list)
    RecyclerView friendRequestsList;

    @Nullable
    @BindView(R.id.friend_requests_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private FriendRequestsListController ctrl;

    private FriendRequestsListAdapter friendRequestsListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_requests_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (FriendsActivity) getActivity();
        bindView(this, view);
        ctrl = new FriendRequestsListController(this);

        setupRequestFriends();

        setupRefresh();

        setupRefresh();

        ctrl.onCreate();
    }


    private void setupRequestFriends() {
        FriendRequestsListAdapter.Factory friendRequestsListFactory = new FriendRequestsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, friendRequestsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        friendRequestsListFactory.setOnClickListener((position, data) -> ctrl.onClickRequestItem(position, data));

        friendRequestsListFactory.setAcceptListener(
                (position, data, holder) -> ctrl.onAcceptRequest(position, data, holder));

        friendRequestsListFactory.setRejectListener(
                (position, data, holder) -> ctrl.onRejectRequest(position, data, holder));

        friendRequestsListAdapter = friendRequestsListFactory.make();
        friendRequestsList.setAdapter(friendRequestsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> ctrl.onRefresh());
    }

    @Override
    public void bindFriendRequests(@NonNull ArrayList<FriendRequestProfile> data) {
        friendRequestsListAdapter.addAll(data);
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFriendAccepted() {
        showAlert(view.getString(R.string.friend_accepted));
    }

    @Override
    public void showFriendRejected() {
        showAlert(view.getString(R.string.friend_rejected));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
