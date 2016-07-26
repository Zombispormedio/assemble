package com.zombispormedio.assemble.rest;

import org.json.JSONObject;



/**
 * Created by Master on 26/07/2016.
 */
public abstract class AbstractResponse<T> {

    public final boolean success;

    public final Error error;

    public final T result;

    public AbstractResponse(boolean success, Error error, T result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }



}
