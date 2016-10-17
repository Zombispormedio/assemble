package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.views.fragments.ITeamsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends Controller {

    private ITeamsView ctx;

    private TeamResource teamResource;

    private TeamSubscription teamSubscription;

    private TeamSubscriber teamSubscriber;

    private boolean refreshing;

    public TeamsController(ITeamsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        teamResource = getResourceComponent().provideTeamResource();
        teamSubscription = getResourceComponent().provideTeamSubscription();
        teamSubscriber = new TeamSubscriber();
        teamSubscription.addSubscriber(teamSubscriber);
        refreshing = false;
    }

    @Override
    public void onCreate() {
        renderTeams();
    }

    private void renderTeams() {
        ArrayList<Team> teams = teamResource.getAll();
        ctx.bindTeams(teams);
    }


    public IOnClickItemListHandler<Team> getOnClickOneTeam() {
        return (position, data) -> {
            Logger.d(position);
            Logger.d(data);
        };
    }

    public void onRefresh() {
        refreshing = true;
        teamSubscription.load();
    }

    private class TeamSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderTeams();
            finishRefresh();
        }

        @Override
        public void notifyFail() {
            finishRefresh();
        }
    }

    private void finishRefresh() {
        if (refreshing) {
            refreshing = false;
            ctx.finishRefresh();
        }
    }


    @Override
    public void onDestroy() {
        teamSubscription.removeSubscriber(teamSubscriber);
        ctx=null;
    }
}
