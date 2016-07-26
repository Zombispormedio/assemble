package com.zombispormedio.assemble.rest;

/**
 * Created by Master on 26/07/2016.
 */
public class DefaultResponse extends AbstractResponse<Result> {
    public DefaultResponse(boolean success, Error error, Result result) {
        super(success, error, result);
    }
}
