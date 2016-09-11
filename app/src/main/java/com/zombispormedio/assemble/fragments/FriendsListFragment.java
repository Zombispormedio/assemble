package com.zombispormedio.assemble.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.adapters.FriendsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.FriendsController;
import com.zombispormedio.assemble.controllers.FriendsListController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IFriendsListView;

import java.util.ArrayList;

import butterknife.BindView;

public class FriendsListFragment extends BaseFragment implements IFriendsListView{

    private FriendsActivity view;

    @BindView(R.id.friends_list)
    RecyclerView _listFriends;

    private FriendsListController ctrl;

    private FriendsRecyclerViewAdapter.Factory _listFriendsFactory;

    private FriendsRecyclerViewAdapter _listFriendsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ctrl= new FriendsListController(this);

        view=(FriendsActivity)getActivity();

        bindView(this, view);

        setupFriends();

        ctrl.onCreate();
    }

    private void setupFriends() {
        _listFriendsFactory = new FriendsRecyclerViewAdapter.Factory();
        _listFriendsAdapter = null;
        AndroidUtils.createListConfiguration(view, _listFriends)
                .divider(true)
                .itemAnimation(true)
                .configure();
        _listFriendsFactory.setOnClickListener(ctrl.getOnClickOneFriend());
    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> data) {
        if (_listFriendsAdapter == null) {
            _listFriendsAdapter = _listFriendsFactory.make(data);
            _listFriends.setAdapter(_listFriendsAdapter);
        } else {
            _listFriendsAdapter.setData(data);
            _listFriendsAdapter.notifyDataSetChanged();
        }
    }
}
