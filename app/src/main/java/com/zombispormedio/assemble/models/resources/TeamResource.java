package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.EditTeam;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.services.interfaces.ITeamService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamResource extends ConceptResource<Team> {

    private ITeamService persistence;

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
}
