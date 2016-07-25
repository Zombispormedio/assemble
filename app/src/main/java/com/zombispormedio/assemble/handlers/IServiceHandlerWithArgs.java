package com.zombispormedio.assemble.handlers;

/**
 * Created by Master on 08/07/2016.
 */
public interface IServiceHandlerWithArgs<T> {
    void onError(T ...args);
    void onSuccess(T ...args);

}
