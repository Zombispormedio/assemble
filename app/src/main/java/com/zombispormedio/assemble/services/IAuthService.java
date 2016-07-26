package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;


/**
 * Created by Master on 10/07/2016.
 */
public interface IAuthService {

    void checkAccess(final IServiceHandler<Result, Error> listener);

    void login(String email, String password, final IServiceHandler<Result, Error> listener);

    void register(String email, String password, final IServiceHandler<Result, Error> listener);

    void signOut(final IServiceHandler<Result, Error> handler);


}
