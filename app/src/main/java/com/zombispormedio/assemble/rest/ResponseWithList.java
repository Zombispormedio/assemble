package com.zombispormedio.assemble.rest;

import java.util.List;

/**
 * Created by Master on 26/07/2016.
 */
public class ResponseWithList<T> {

    public final boolean success;

    public final Error error;

    public final List<T> result;

    public ResponseWithList(boolean success, Error error, List<T> result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }
    public ResponseWithList(){

        this.success = false;
        this.error=null;
        this.result=null;
    }
}
