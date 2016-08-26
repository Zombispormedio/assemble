package com.zombispormedio.assemble.rest.responses;


import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.rest.Error;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsResponse extends AbstractResponse<FriendRequestProfile[]> {

    public FriendRequestsResponse(boolean success, Error error,
            FriendRequestProfile[] result) {
        super(success, error, result);
    }

    public ArrayList<FriendRequestProfile> getResult() {
        return new ArrayList<>(Arrays.asList(result));
    }
}
