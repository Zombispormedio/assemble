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
    RecyclerView _listFriendRequests;

    private FriendRequestsListController ctrl;

    private FriendRequestsRecyclerViewAdapter.Factory _listFriendRequestsFactory;

    private FriendRequestsRecyclerViewAdapter _listFriendRequestsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_requests, container, false);
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
        _listFriendRequestsFactory = new FriendRequestsRecyclerViewAdapter.Factory();
        _listFriendRequestsAdapter = null;
        AndroidUtils.createListConfiguration(view, _listFriendRequests)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        _listFriendRequestsFactory.setOnClickListener(ctrl.getOnClickOneRequest());
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
}
