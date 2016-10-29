package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.lists.MeetingsListAdapter;
import com.zombispormedio.assemble.controllers.MeetingsController;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.fragments.IMeetingsView;

import java.util.ArrayList;

import butterknife.BindView;


public class MeetingsFragment extends BaseFragment implements IMeetingsView {

    private HomeActivity view;

    private MeetingsController ctrl;

    @BindView(R.id.meetings_list)
    RecyclerView meetingsList;

    @BindView(R.id.meetings_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private MeetingsListAdapter meetingsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meetings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (HomeActivity) getActivity();
        bindView(this, view);
        ctrl = new MeetingsController(this);

        setupMeetings();

        setupRefresh();

        ctrl.onCreate();
    }

    private void setupMeetings() {
        MeetingsListAdapter.Factory meetingsListFactory = new MeetingsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, meetingsList)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        meetingsListFactory.setOnClickListener(ctrl::onClickOneTeam);
        meetingsListFactory.setBookmarkListener(ctrl::onBookmark);
        meetingsListAdapter = meetingsListFactory.make();
        meetingsList.setAdapter(meetingsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> ctrl.onRefresh());
    }

    @Override
    public void bindMeetings(ArrayList<Meeting> data) {
        meetingsListAdapter.addAll(data);
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
