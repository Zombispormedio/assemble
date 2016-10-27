package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;

import com.zombispormedio.assemble.models.editors.TeamEditor;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public interface ITeamService {

    void getAll(final IServiceHandler<ArrayList<Team>, Error> handler);


    void create(TeamEditor team, IServiceHandler<Team, Error> handler);


    void uploadImage(int teamId, File file, IServiceHandler<Team, Error> handler);
}
