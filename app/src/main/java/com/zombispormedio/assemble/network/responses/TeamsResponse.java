package com.zombispormedio.assemble.network.responses;


import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.network.Error;


/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamsResponse extends ArrayResponse<Team> {

    public TeamsResponse(boolean success, Error error,
            Team[] result) {
        super(success, error, result);
    }
}
