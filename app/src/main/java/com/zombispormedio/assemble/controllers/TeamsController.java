package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.ITeamsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends AbstractController {

    private ITeamsView ctx;
    private TeamResource teamResource;
    private CurrentUser user;

    public TeamsController(ITeamsView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();
        teamResource= ResourceFactory.createTeamResource();
    }

    @Override
    public void onCreate() {
        setupTeams();
    }

    private void setupTeams() {
        if(user.getTeamsCount()>0){
            ctx.bindTeams(user.getTeams());
        }

        getTeams();
    }

    private void getTeams(){
        teamResource.getAll(new IServiceHandler<ArrayList<Team>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Team> result) {
                ctx.bindTeams(result);
            }
        });
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
}
