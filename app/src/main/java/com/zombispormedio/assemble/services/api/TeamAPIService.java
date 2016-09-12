package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
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
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {

                        try {
                            TeamsResponse res= JsonBinder.toTeamsResponse(args[0]);

                            if(res.success){
                                handler.onSuccess(res.getResult());
                            }else{
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .get();
    }
}
