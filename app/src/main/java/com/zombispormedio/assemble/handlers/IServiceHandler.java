package com.zombispormedio.assemble.handlers;

/**
 * Created by Master on 16/07/2016.
 */
public interface IServiceHandler<T, E> {
    void onError(E ...args);
    void onSuccess(T ...args);
}
