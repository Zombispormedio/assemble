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
import com.zombispormedio.assemble.adapters.TeamsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.TeamsController;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.ITeamsView;

import java.util.ArrayList;

import butterknife.BindView;


public class TeamsFragment extends BaseFragment implements ITeamsView {

    private HomeActivity view;

    private TeamsController ctrl;

    @BindView(R.id.teams_list)
    RecyclerView teamsList;

    @BindView(R.id.teams_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private TeamsRecyclerViewAdapter.Factory teamsListFactory;

    private TeamsRecyclerViewAdapter teamsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (HomeActivity) getActivity();
        bindView(this, view);
        ctrl = new TeamsController(this);

        setupTeams();

        setupRefresh();

        ctrl.onCreate();
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finishRefresh();
            }
        });
    }


    private void setupTeams() {
        teamsListFactory = new TeamsRecyclerViewAdapter.Factory();
        AndroidUtils.createListConfiguration(view, teamsList)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        teamsListFactory.setOnClickListener(ctrl.getOnClickOneTeam());
        teamsListAdapter = teamsListFactory.make();
        teamsList.setAdapter(teamsListAdapter);
    }


    @Override
    public void bindTeams(ArrayList<Team> data) {
        teamsListAdapter.setData(data);
        teamsListAdapter.notifyDataSetChanged();
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
