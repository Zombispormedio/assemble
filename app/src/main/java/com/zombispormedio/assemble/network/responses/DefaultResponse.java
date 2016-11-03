package com.zombispormedio.assemble.network.responses;

import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;

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
