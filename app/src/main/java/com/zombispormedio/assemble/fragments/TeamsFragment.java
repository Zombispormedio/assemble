package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.TeamsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.TeamsController;
import com.zombispormedio.assemble.views.ITeamsView;


public class TeamsFragment extends Fragment implements ITeamsView {

    private HomeActivity ctx;

    private TeamsController ctrl;

    private RecyclerView _listTeams;

    private TeamsRecyclerViewAdapter.Factory _listTeamsFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctrl = new TeamsController(this);

        ctx = (HomeActivity) getActivity();

        _listTeams = (RecyclerView) ctx.findViewById(R.id.teams_list);

        _listTeamsFactory = new TeamsRecyclerViewAdapter.Factory();
    }
}
