package com.zombispormedio.assemble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.FriendsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.FriendsListController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IFriendsListView;

import java.util.ArrayList;

import butterknife.BindView;

public class FriendsListFragment extends BaseFragment implements IFriendsListView {

    private FriendsActivity view;

    @BindView(R.id.friends_list)
    RecyclerView friendsList;

    private FriendsListController ctrl;

    private FriendsRecyclerViewAdapter.Factory friendsListFactory;

    private FriendsRecyclerViewAdapter friendsListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        ctrl.onCreate();
    }

    private void setupFriends() {
        friendsListFactory = new FriendsRecyclerViewAdapter.Factory();
        ;
        AndroidUtils.createListConfiguration(view, friendsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        friendsListFactory.setOnClickListener(ctrl.getOnClickOneFriend());
        friendsListAdapter = friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);
    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> data) {
        friendsListAdapter.setData(data);
        friendsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
