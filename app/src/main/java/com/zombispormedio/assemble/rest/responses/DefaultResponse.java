package com.zombispormedio.assemble.rest.responses;

import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.rest.responses.AbstractResponse;

/**
 * Created by Master on 26/07/2016.
 */
public class DefaultResponse extends AbstractResponse<Result> {
    public DefaultResponse(boolean success, Error error, Result result) {
        super(success, error, result);
    }
}