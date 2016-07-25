package com.zombispormedio.assemble.handlers;

/**
 * Created by Master on 16/07/2016.
 */
public interface IServiceHandler2<T, E> {
    void onError(E ...args);
    void onSuccess(T ...args);
}
