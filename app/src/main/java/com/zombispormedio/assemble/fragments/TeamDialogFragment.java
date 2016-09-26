package com.zombispormedio.assemble.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.lists.TeamListDialogAdapter;
import com.zombispormedio.assemble.adapters.lists.TeamsListAdapter;
import com.zombispormedio.assemble.controllers.TeamDialogController;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.ITeamDialogView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamDialogFragment extends BaseDialogFragment implements ITeamDialogView {


    private Activity view;

    private TeamDialogController ctrl;

    @BindView(R.id.teams_list)
    RecyclerView teamsList;

    private IOnClickItemListHandler<Team> listener;

    private TeamListDialogAdapter.Factory teamsListFactory;

    private TeamListDialogAdapter teamsListAdapter;

    public TeamDialogFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        setupDialog();
        View view = inflater.inflate(R.layout.fragment_team_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view =  getActivity();
        bindView(view);

        ctrl=new TeamDialogController(this);

        setupTeams();

        ctrl.onCreate();
    }

    private void setupTeams() {

        teamsListFactory = new TeamListDialogAdapter.Factory();
        AndroidUtils.createListConfiguration(view, teamsList)
                .itemAnimation(true)
                .divider(true)
                .configure();
        teamsListFactory.setOnClickListener(new IOnClickItemListHandler<Team>() {
            @Override
            public void onClick(int position, Team data) {
                if(listener!=null){
                    listener.onClick(position, data);
                }
                dismiss();
            }
        });
        teamsListAdapter = teamsListFactory.make();
        teamsList.setAdapter(teamsListAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        setupSize();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupSize();
    }

    private void setupDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setTitle(R.string.teams_tab_title);
            dialog.setCanceledOnTouchOutside(true);
        }
    }

    private void setupSize(){
        Window window = getDialog().getWindow();

        if(window!=null){
            window.setLayout((int)getResources().getDimension(R.dimen.spinner_dialog_width)
                    , (int)getResources().getDimension(R.dimen.spinner_dialog_height));
            window.setGravity(Gravity.CENTER);
        }

    }


    @Override
    public void bindTeams(ArrayList<Team> data) {
        teamsListAdapter.setData(data);
    }


    public void setOnItemClickedListener(
            IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

}
