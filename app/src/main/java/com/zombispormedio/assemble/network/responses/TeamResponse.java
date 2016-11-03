package com.zombispormedio.assemble.network.responses;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.network.Error;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamResponse extends AbstractResponse<Team> {

    public TeamResponse(boolean success, Error error,
            Team result) {
        super(success, error, result);
    }
}
