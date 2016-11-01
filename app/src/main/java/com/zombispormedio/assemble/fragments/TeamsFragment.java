package com.zombispormedio.assemble.fragments;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.lists.TeamsListAdapter;
import com.zombispormedio.assemble.controllers.TeamsController;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.fragments.ITeamsView;

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


public class TeamsFragment extends BaseFragment implements ITeamsView {

    private HomeActivity view;

    private TeamsController ctrl;


    @BindView(R.id.teams_list)
    RecyclerView teamsList;


    @BindView(R.id.teams_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private TeamsListAdapter teamsListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (HomeActivity) getActivity();
        bindView(this, view);
        ctrl = new TeamsController(this);

        setupTeams();

        setupRefresh();

        ctrl.onCreate();
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> ctrl.onRefresh());
    }


    private void setupTeams() {
        TeamsListAdapter.Factory teamsListFactory = new TeamsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, teamsList)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        teamsListFactory.setOnClickListener(ctrl::onClickOneTeam);

        teamsListFactory.setStarCheckerListener(ctrl::onStarChecker);
        teamsListAdapter = teamsListFactory.make();
        teamsList.setAdapter(teamsListAdapter);
    }


    @Override
    public void bindTeams(@NonNull ArrayList<Team> data) {
        teamsListAdapter.addAll(data);
    }

    @Override
    public void updateTeam(int position, Team team) {
        teamsListAdapter.updateItemAt(position, team);
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
