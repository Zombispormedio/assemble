package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.views.ITeamsView;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends AbstractController {

    private ITeamsView ctx;

    public TeamsController(ITeamsView ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate() {

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
