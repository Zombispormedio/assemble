package com.zombispormedio.assemble.controllers;

/**
 * Created by Master on 08/07/2016.
 */
public interface IBaseListener<T> {
    void onError(T ...args);
    void onSuccess(T ...args);

}
