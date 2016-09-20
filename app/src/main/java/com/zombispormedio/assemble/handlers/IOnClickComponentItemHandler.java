package com.zombispormedio.assemble.handlers;

/**
 * Created by Xavier Serrano on 20/09/2016.
 */

public interface IOnClickComponentItemHandler<T, H> {

    void onClick(int position, T data, H holder);
}
