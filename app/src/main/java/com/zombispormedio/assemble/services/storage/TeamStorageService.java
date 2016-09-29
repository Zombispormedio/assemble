package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.TeamDAO;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class TeamStorageService extends StorageService<TeamDAO, Team> {

    public TeamStorageService() {
         super(new LocalStorage<TeamDAO, Team>(TeamDAO.class, new TeamDAO.Factory()));
    }


}
