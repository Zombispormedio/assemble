package com.zombispormedio.assemble.handlers;

/**
 * Created by Master on 25/07/2016.
 */
public interface IServiceHandler<T> {
    void onError(T ...args);
    void onSuccess(T ...args);
}
