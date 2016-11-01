package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IAuthService {

    void checkAccess(final IServiceHandler<Result, Error> listener);

    void login(Auth auth, final IServiceHandler<Result, Error> listener);

    void register(Auth auth, final IServiceHandler<Result, Error> listener);

    void signOut(final IServiceHandler<Result, Error> handler);

    void refreshGCM(String gcmToken, final IServiceHandler<Result, Error> handler);


}
