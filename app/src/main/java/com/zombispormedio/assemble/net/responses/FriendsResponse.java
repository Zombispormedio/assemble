package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.net.Error;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class FriendsResponse extends ArrayResponse<FriendProfile> {

    public FriendsResponse(boolean success, Error error,
            FriendProfile[] result) {
        super(success, error, result);
    }
}
