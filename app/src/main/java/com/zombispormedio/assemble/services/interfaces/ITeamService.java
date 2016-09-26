package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;

import com.zombispormedio.assemble.models.EditTeam;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public interface ITeamService {

    void getAll(final IServiceHandler<ArrayList<Team>, Error> handler);


    void create(EditTeam team, IServiceHandler<Team, Error> handler);
}
