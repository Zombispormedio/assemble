package com.zombispormedio.assemble.rest.responses;

import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.rest.Error;

/**
 * Created by Master on 27/07/2016.
 */
public class ProfileResponse extends AbstractResponse<User> {
    public ProfileResponse(boolean success, Error error, User result) {
        super(success, error, result);
    }
}
