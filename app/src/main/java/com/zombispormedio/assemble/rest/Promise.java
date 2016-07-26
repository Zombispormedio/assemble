package com.zombispormedio.assemble.rest;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;

/**
 * Created by Master on 26/07/2016.
 */
public class Promise {

    private String result;

    private IPromiseHandler handler;

    public Promise(String result, IPromiseHandler handler) {
        this.result = result;
        this.handler = handler;
    }

    public String getResult() {
        return result;
    }

    public IPromiseHandler getHandler() {
        return handler;
    }
}