package com.zombispormedio.assemble.rest.responses;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.rest.Error;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class FriendsResponse extends AbstractResponse<FriendProfile[]> {

    public FriendsResponse(boolean success, Error error,
            FriendProfile[] result) {
        super(success, error, result);
    }

    public ArrayList<FriendProfile> getResult() {
        return new ArrayList<>(Arrays.asList(result));
    }
}
