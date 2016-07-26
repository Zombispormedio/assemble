package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.rest.Error;

/**
 * Created by Master on 10/07/2016.
 */
public interface IPersistenceService<T> {

    void retrieve(final IServiceHandler<T, Error> handler);

}
