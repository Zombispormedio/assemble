package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.MeetingsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.MeetingsController;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IMeetingsView;

import java.util.ArrayList;

import butterknife.BindView;


public class MeetingsFragment extends BaseFragment implements IMeetingsView {

    private HomeActivity view;

    private MeetingsController ctrl;

    @BindView(R.id.meetings_list)
    RecyclerView meetingsList;

    private MeetingsRecyclerViewAdapter.Factory meetingsListFactory;

    private MeetingsRecyclerViewAdapter meetingsListAdapter;

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

        ctrl.onCreate();
    }

    private void setupMeetings() {
        meetingsListFactory = new MeetingsRecyclerViewAdapter.Factory();
        AndroidUtils.createListConfiguration(view, meetingsList)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        meetingsListFactory.setOnClickListener(ctrl.getOnClickOneTeam());
        meetingsListAdapter = meetingsListFactory.make();
        meetingsList.setAdapter(meetingsListAdapter);
    }

    @Override
    public void bindMeetings(ArrayList<Meeting> data) {
            meetingsListAdapter.setData(data);
            meetingsListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
