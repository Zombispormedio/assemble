package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.editors.EditTeam;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.services.interfaces.ITeamService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamResource extends AbstractResource<Team> {

    private final ITeamService persistence;

    @Inject
    public TeamResource(ITeamService persistence,
            IStorageService<Team> storage) {
        super(storage);
        this.persistence = persistence;
    }

    public void getAll(IServiceHandler<ArrayList<Team>, Error> handler){
        persistence.getAll(handler);
    }

    public void create(EditTeam editTeam, final IServiceHandler<Team, Error> handler){

        persistence.create(editTeam, new ServiceHandler<Team, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Team result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });

    }

    public void uploadImage(int teamId, String path, final IServiceHandler<Team, Error> handler){
        persistence.uploadImage(teamId, new File(path), new ServiceHandler<Team, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Team result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });
    }

}
