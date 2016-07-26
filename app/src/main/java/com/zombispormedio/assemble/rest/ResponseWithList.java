package com.zombispormedio.assemble.rest;

/**
 * Created by Master on 26/07/2016.
 */
public class ResponseWithList<T> {

    public final boolean success;

    public final Error error;

    public final  List<T> result;

    public ResponseWithArray(boolean success, Error error, List<T> result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }
}
