package com.zombispormedio.assemble.network;

import com.zombispormedio.assemble.handlers.IPromiseHandler;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class Promise {

    private String result;

    private boolean online;

    private IPromiseHandler handler;

    public Promise(String result, IPromiseHandler handler) {
        this.result = result;
        this.handler = handler;
        online = ConnectionState.getInstance().isConnected();
    }

    public Promise() {
        online = ConnectionState.getInstance().isConnected();
    }

    public String getResult() {
        return result;
    }

    public IPromiseHandler getHandler() {
        return handler;
    }

    public void handle() {
        if (online) {
            handler.onSuccess(result);
        } else {
            handler.onNotConnected();
        }

    }
}
