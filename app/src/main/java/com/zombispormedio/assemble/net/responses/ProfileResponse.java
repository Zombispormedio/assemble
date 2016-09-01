package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.Error;

/**
 * Created by Xavier Serrano on 27/07/2016.
 */
public class ProfileResponse extends AbstractResponse<UserProfile> {

    public ProfileResponse(boolean success, Error error, UserProfile result) {
        super(success, error, result);
    }
}
