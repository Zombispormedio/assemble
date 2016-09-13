package com.zombispormedio.assemble.handlers;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ServiceHandler<T, E> implements IServiceHandler<T, E> {

    @Override
    public void onError(E error) {

    }

    @Override
    public void onSuccess(T result) {

    }

    @Override
    public void onNotConnected() {

    }
}
