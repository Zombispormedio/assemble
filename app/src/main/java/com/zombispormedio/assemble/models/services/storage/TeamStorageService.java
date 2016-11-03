package com.zombispormedio.assemble.models.services.storage;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.models.services.storage.dao.TeamDAO;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class TeamStorageService extends StorageService<TeamDAO, Team> {

    public TeamStorageService() {
        super(new LocalStorage<>(TeamDAO.class, new TeamDAO.Factory()));
    }

    public TeamStorageService(
            LocalStorage<TeamDAO, Team> storage) {
        super(storage);
    }

    public void star(int teamId) {
        TeamDAO teamDAO = storage.getById(teamId);
        storage.begin();

        teamDAO.starred = !teamDAO.starred;

        storage.commit();

    }
}
