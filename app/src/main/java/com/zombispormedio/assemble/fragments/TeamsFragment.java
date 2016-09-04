package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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


public class TeamsFragment extends Fragment implements ITeamsView {

    private HomeActivity view;

    private TeamsController ctrl;

    private RecyclerView _listTeams;

    private TeamsRecyclerViewAdapter.Factory _listTeamsFactory;

    private TeamsRecyclerViewAdapter _listsTeamsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctrl = new TeamsController(this);

        view = (HomeActivity) getActivity();

        _listTeams = (RecyclerView) view.findViewById(R.id.teams_list);

        _listTeamsFactory = new TeamsRecyclerViewAdapter.Factory();
        _listsTeamsAdapter=null;

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setupTeams();

        ctrl.onCreate();
    }

    private void setupTeams() {
        AndroidUtils.setupScrollList(view, _listTeams);
        _listTeamsFactory.setOnClickListener(ctrl.getOnClickOneTeam());

    }


    @Override
    public void bindTeams(ArrayList<Team> data) {
        if(_listsTeamsAdapter==null){
            _listsTeamsAdapter=_listTeamsFactory.make(data);
            _listTeams.setAdapter(_listsTeamsAdapter);
        }else{
            _listsTeamsAdapter.setData(data);
            _listsTeamsAdapter.notifyDataSetChanged();
        }
    }
}
