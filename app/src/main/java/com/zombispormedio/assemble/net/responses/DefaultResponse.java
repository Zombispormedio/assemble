package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class DefaultResponse extends AbstractResponse<Result> {

    public DefaultResponse(boolean success, Error error, Result result) {
        super(success, error, result);

    }

    public Result getResult() {
        return result;
    }


}
