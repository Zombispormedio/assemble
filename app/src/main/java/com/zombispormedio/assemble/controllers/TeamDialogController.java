package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.views.fragments.ITeamDialogView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamDialogController extends Controller {

    private ITeamDialogView ctx;

    private TeamResource teamResource;

    public TeamDialogController(ITeamDialogView ctx) {
        super(ctx.getParent());
        this.ctx=ctx;

        teamResource = getResourceComponent().provideTeamResource();
    }

    @Override
    public void onCreate() {
        setupTeams();
    }

    private void setupTeams() {
        ArrayList<Team> teams = teamResource.getAll();
        ctx.bindTeams(teams);
    }

}
