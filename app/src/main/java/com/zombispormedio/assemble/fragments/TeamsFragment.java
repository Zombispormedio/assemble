package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
    RecyclerView _listTeams;

    private TeamsRecyclerViewAdapter.Factory _listTeamsFactory;

    private TeamsRecyclerViewAdapter _listTeamsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ctrl = new TeamsController(this);

        view = (HomeActivity) getActivity();

        bindView(this, view);

        _listTeamsFactory = new TeamsRecyclerViewAdapter.Factory();
        _listTeamsAdapter =null;

        setupTeams();

        ctrl.onCreate();
    }


    private void setupTeams() {
        AndroidUtils.createListConfiguration(view, _listTeams)
                .divider(true)
                .itemAnimation(true)
                .configure();
        _listTeamsFactory.setOnClickListener(ctrl.getOnClickOneTeam());

    }


    @Override
    public void bindTeams(ArrayList<Team> data) {
        if(_listTeamsAdapter ==null){
            _listTeamsAdapter =_listTeamsFactory.make(data);
            _listTeams.setAdapter(_listTeamsAdapter);
        }else{
            _listTeamsAdapter.setData(data);
            _listTeamsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }
}
