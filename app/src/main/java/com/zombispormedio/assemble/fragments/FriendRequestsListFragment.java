package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.FriendRequestsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.FriendRequestsListController;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IFriendRequestHolder;
import com.zombispormedio.assemble.views.IFriendRequestsListView;

import java.util.ArrayList;

import butterknife.BindView;


public class FriendRequestsListFragment extends BaseFragment implements IFriendRequestsListView {

    private FriendsActivity view;

    @BindView(R.id.req_friends_list)
    RecyclerView friendRequestsList;

    @BindView(R.id.friend_requests_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private FriendRequestsListController ctrl;

    private FriendRequestsRecyclerViewAdapter.Factory friendRequestsListFactory;

    private FriendRequestsRecyclerViewAdapter friendRequestsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        friendRequestsListFactory = new FriendRequestsRecyclerViewAdapter.Factory();
        AndroidUtils.createListConfiguration(view, friendRequestsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        friendRequestsListFactory.setOnClickListener(new IOnClickItemListHandler<FriendRequestProfile>() {
            @Override
            public void onClick(int position, FriendRequestProfile data) {
                ctrl.onClickRequestItem(position, data);
            }
        });

        friendRequestsListFactory.setAcceptListener(
                new IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder>() {
                    @Override
                    public void onClick(int position, FriendRequestProfile data, IFriendRequestHolder holder) {
                        ctrl.onAcceptRequest(position, data, holder);
                    }
                });

        friendRequestsListFactory.setRejectListener(
                new IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder>() {
                    @Override
                    public void onClick(int position, FriendRequestProfile data, IFriendRequestHolder holder) {
                        ctrl.onRejectRequest(position, data, holder);
                    }
                });

        friendRequestsListAdapter = friendRequestsListFactory.make();
        friendRequestsList.setAdapter(friendRequestsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finishRefresh();
            }
        });
    }

    @Override
    public void bindFriendRequests(ArrayList<FriendRequestProfile> data) {
        friendRequestsListAdapter.setData(data);
        friendRequestsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
