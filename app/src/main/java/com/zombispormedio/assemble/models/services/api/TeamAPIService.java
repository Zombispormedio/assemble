package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.editors.TeamEditor;
import com.zombispormedio.assemble.models.services.interfaces.ITeamService;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.FileBody;
import com.zombispormedio.assemble.network.JsonBinder;
import com.zombispormedio.assemble.network.Result;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamAPIService implements ITeamService {

    private final APIConfiguration api;

    public TeamAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void getAll(final IServiceHandler<ArrayList<Team>, Error> handler) {
        api.RestWithAuth("/teams")
                .handler(DeferUtils.deferTeams(handler))
                .get();
    }

    @Override
    public void create(TeamEditor team, IServiceHandler<Team, Error> handler) {
        api.RestWithAuth("/team")
                .handler(DeferUtils.deferTeam(handler))
                .post(JsonBinder.fromEditTeam(team));
    }

    @Override
    public void uploadImage(int teamId, @NonNull File file, IServiceHandler<Team, Error> handler) {
        api.RestWithAuth("/team/:id/image")
                .params("id", teamId)
                .handler(DeferUtils.deferTeam(handler))
                .patch(new FileBody(file, "image/*", "image", file.getName()));
    }

    @Override
    public void star(int teamId, IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/team/:id/star")
                .params("id", teamId)
                .handler(DeferUtils.defer(handler))
                .put();
    }


}
