package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.fragments.ITeamsView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends Controller {

    @Nullable
    private ITeamsView ctx;

    private final TeamResource teamResource;

    private final TeamSubscription teamSubscription;

    @NonNull
    private final TeamSubscriber teamSubscriber;

    private boolean refreshing;

    public TeamsController(@NonNull ITeamsView ctx) {
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


    public void onClickOneTeam(int position, Team team) {

    }

    public void onRefresh() {
        refreshing = true;
        teamSubscription.load();
    }

    public void onStarChecker(int position, @NonNull Team team) {

        teamResource.star(team.id, new ServiceHandler<Result, Error>() {
            @Override
            public void onSuccess(Result result) {
                renderTeams();
            }
        });
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
        super.onDestroy();
        teamSubscription.removeSubscriber(teamSubscriber);
        ctx = null;
    }
}
