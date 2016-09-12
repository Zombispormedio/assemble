package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.ITeamService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamResource {
    private ITeamService persistence;

    public TeamResource(ITeamService persistence) {
        this.persistence = persistence;
    }

    public void getAll(IServiceHandler<ArrayList<Team>, Error> handler){
        persistence.getAll(handler);
    }
}
