package com.zombispormedio.assemble.listeners;

/**
 * Created by Master on 08/07/2016.
 */
public interface IListenerWithArgs<T> {
    void onError(T ...args);
    void onSuccess(T ...args);

}
