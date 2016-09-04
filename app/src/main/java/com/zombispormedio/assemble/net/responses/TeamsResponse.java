package com.zombispormedio.assemble.net.responses;


import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 04/09/2016.
 */
public class TeamsResponse extends AbstractResponse<Team[]>  {

    public TeamsResponse(boolean success, Error error,
            Team[] result) {
        super(success, error, result);
    }
    public ArrayList<Team> getResult() {
        return new ArrayList<>(Arrays.asList(result));
    }
}
