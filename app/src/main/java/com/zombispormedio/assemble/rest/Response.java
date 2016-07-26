package com.zombispormedio.assemble.rest;

import org.json.JSONObject;



/**
 * Created by Master on 26/07/2016.
 */
public class Response {

    public final boolean success;

    public final Error error;

    public final Result result;

    public Response(boolean success, Error error, Result result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }
}
