package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.services.interfaces.ITeamService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.network.Error;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class TeamLoader implements ILoader {

    private final ITeamService apiService;

    private final IStorageService<Team> storageService;

    public TeamLoader(ITeamService apiService,
            IStorageService<Team> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(@NonNull final SuccessHandler handler) {
        apiService.getAll(new ServiceHandler<ArrayList<Team>, Error>() {
            @Override
            public void onSuccess(ArrayList<Team> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }

            @Override
            public void onError(Error error) {
                handler.onError();
            }

            @Override
            public void onNotConnected() {
                handler.onError();
            }
        });
    }
}
