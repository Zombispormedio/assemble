package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.EditTeam;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.FileBody;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.TeamsResponse;
import com.zombispormedio.assemble.services.interfaces.ITeamService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamAPIService implements ITeamService {

    private APIConfiguration api;

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
    public void create(EditTeam team, IServiceHandler<Team, Error> handler) {
        api.RestWithAuth("/team")
                .handler(DeferUtils.deferTeam(handler))
                .post(JsonBinder.fromEditTeam(team));
    }

    @Override
    public void uploadImage(int teamId, File file, IServiceHandler<Team, Error> handler) {
        api.RestWithAuth("/team/:id/image")
                .params("id", teamId)
                .handler(DeferUtils.deferTeam(handler))
                .patch(new FileBody(file, "image/*", "image", file.getName()));
    }


}
