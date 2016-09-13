package com.zombispormedio.assemble.handlers;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class PromiseHandler  implements IPromiseHandler{

    private IServiceHandler handler;

    public PromiseHandler(IServiceHandler handler) {
        this.handler = handler;
    }


    @Override
    public void onSuccess(String... args) {

    }

    @Override
    public void onNotConnected() {
        handler.onNotConnected();
    }
}
