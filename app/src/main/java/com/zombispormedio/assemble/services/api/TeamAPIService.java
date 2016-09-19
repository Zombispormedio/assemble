package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.TeamsResponse;
import com.zombispormedio.assemble.services.interfaces.ITeamService;

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
                .handler(deferTeams(handler))
                .get();
    }

    private PromiseHandler deferTeams(IServiceHandler<ArrayList<Team>, Error> handler){
        return new PromiseHandler<TeamsResponse,  ArrayList<Team>>(handler){

            @Override
            protected TeamsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toTeamsResponse(arg);
            }

            @Override
            protected ArrayList<Team> getResult(TeamsResponse res) {
                return res.getResult();
            }
        };
    }
}
