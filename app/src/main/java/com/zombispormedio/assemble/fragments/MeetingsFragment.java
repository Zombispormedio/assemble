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
    RecyclerView _listMeetings;

    private MeetingsRecyclerViewAdapter.Factory _listMeetingsFactory;

    private MeetingsRecyclerViewAdapter _listMeetingsAdapter;

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

        _listMeetingsFactory = new MeetingsRecyclerViewAdapter.Factory();
        _listMeetingsAdapter = null;

        setupMeetings();

        ctrl.onCreate();
    }

    private void setupMeetings() {
        AndroidUtils.createListConfiguration(view, _listMeetings)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        _listMeetingsFactory.setOnClickListener(ctrl.getOnClickOneTeam());
    }

    @Override
    public void bindMeetings(ArrayList<Meeting> data) {
        if (_listMeetingsAdapter == null) {
            _listMeetingsAdapter = _listMeetingsFactory.make(data);
            _listMeetings.setAdapter(_listMeetingsAdapter);
        } else {
            _listMeetingsAdapter.setData(data);
            _listMeetingsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
