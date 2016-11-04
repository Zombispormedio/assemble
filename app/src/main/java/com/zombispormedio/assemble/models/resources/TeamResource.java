package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.editors.TeamEditor;
import com.zombispormedio.assemble.models.services.interfaces.ITeamService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.models.services.storage.TeamStorageService;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamResource extends AbstractResource<Team> {

    private final ITeamService persistence;

    private TeamSubscription teamSubscription;

    @Inject
    public TeamResource(ITeamService persistence,
            IStorageService<Team> storage) {
        super(storage);
        this.persistence = persistence;
    }

    public void getAll(IServiceHandler<ArrayList<Team>, Error> handler) {
        persistence.getAll(handler);
    }

    public void create(TeamEditor teamEditor, final IServiceHandler<Team, Error> handler) {

        persistence.create(teamEditor, new ServiceHandler<Team, Error>(handler) {
            @Override
            public void onSuccess(Team result) {
                storage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });

    }

    public void uploadImage(int teamId, @NonNull String path, final IServiceHandler<Team, Error> handler) {
        persistence.uploadImage(teamId, new File(path), new ServiceHandler<Team, Error>(handler) {
            @Override
            public void onSuccess(Team result) {
                storage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });
    }

    public void star(int teamId) {
        persistence.star(teamId, new ServiceHandler<Result, Error>() {
            @Override
            public void onSuccess(Result result) {
                getTeamStorage().star(teamId);
                haveOneChanged(teamId);
            }
        });
    }


    @NonNull
    private TeamStorageService getTeamStorage() {
        return (TeamStorageService) storage;
    }

    public void setTeamSubscription(TeamSubscription teamSubscription) {
        this.teamSubscription = teamSubscription;
    }

    public void haveOneChanged(int id) {
        if (teamSubscription != null) {
            teamSubscription.haveOneChanged(id);
        }
    }

    public Team getById(int id){
        return getTeamStorage().getByID(id);
    }

}
