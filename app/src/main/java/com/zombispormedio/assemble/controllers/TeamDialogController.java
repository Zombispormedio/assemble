package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.views.fragments.ITeamDialogView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamDialogController extends Controller {


    private ITeamDialogView ctx;

    private final TeamResource teamResource;

    public TeamDialogController(@NonNull ITeamDialogView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;

        teamResource = getResourceComponent().provideTeamResource();
    }

    @Override
    public void onCreate() {
        renderTeams();
    }

    private void renderTeams() {
        ArrayList<Team> teams = teamResource.getAll();
        ctx.bindTeams(teams);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
