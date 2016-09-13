package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.ITeamService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class TeamLoader implements ILoader {

    private ITeamService apiService;

    private IStorageService<Team> storageService;

    public TeamLoader(ITeamService apiService,
            IStorageService<Team> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getAll(new IServiceHandler<ArrayList<Team>, Error>() {
            @Override
            public void onError(Error error) {

            }

            @Override
            public void onSuccess(ArrayList<Team> result) {
                storageService.createOrUpdateAll(result);
                handler.onSuccess();
            }
        });
    }
}
