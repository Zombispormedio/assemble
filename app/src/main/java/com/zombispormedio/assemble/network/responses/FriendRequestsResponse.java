package com.zombispormedio.assemble.network.responses;


import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.network.Error;


/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsResponse extends ArrayResponse<FriendRequestProfile> {

    public FriendRequestsResponse(boolean success, Error error,
            FriendRequestProfile[] result) {
        super(success, error, result);
    }


}
