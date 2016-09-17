package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.FriendRequestsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.FriendRequestsListController;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IFriendRequestsListView;

import java.util.ArrayList;

import butterknife.BindView;


public class FriendRequestsListFragment extends BaseFragment implements IFriendRequestsListView {

    private FriendsActivity view;

    @BindView(R.id.req_friends_list)
    RecyclerView friendRequestsList;

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
        ctrl= new FriendRequestsListController(this);

        view=(FriendsActivity)getActivity();

        bindView(this, view);

        setupRequestFriends();

        ctrl.onCreate();
    }

    private void setupRequestFriends() {
        friendRequestsListFactory = new FriendRequestsRecyclerViewAdapter.Factory();
        AndroidUtils.createListConfiguration(view, friendRequestsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        friendRequestsListFactory.setOnClickListener(ctrl.getOnClickOneRequest());
        friendRequestsListAdapter=friendRequestsListFactory.make();
        friendRequestsList.setAdapter(friendRequestsListAdapter);
    }

    @Override
    public void bindFriendRequests(ArrayList<FriendRequestProfile> data) {
        friendRequestsListAdapter.setData(data);
        friendRequestsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
