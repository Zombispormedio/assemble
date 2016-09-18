package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.views.ITeamsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends Controller {

    private ITeamsView ctx;

    private TeamResource teamResource;

    private TeamSubscription teamSubscription;

    private TeamSubscriber teamSubscriber;


    public TeamsController(ITeamsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        teamResource = getResourceComponent().provideTeamResource();
        teamSubscription = getResourceComponent().provideTeamSubscription();
        teamSubscriber = new TeamSubscriber();
        teamSubscription.addSubscriber(teamSubscriber);
    }

    @Override
    public void onCreate() {
        setupTeams();
    }

    private void setupTeams() {
        bindTeams();
        teamSubscription.load();
    }


    private void bindTeams() {
        ArrayList<Team> teams = teamResource.getAll();

        if (teams.size() > 0) {
            ctx.bindTeams(teams);
        }
    }


    public IOnClickItemListHandler<Team> getOnClickOneTeam() {
        return new IOnClickItemListHandler<Team>() {
            @Override
            public void onClick(int position, Team data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }

    private class TeamSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            bindTeams();
        }
    }

    @Override
    public void onDestroy() {
        teamSubscription.removeSubscriber(teamSubscriber);
    }
}
