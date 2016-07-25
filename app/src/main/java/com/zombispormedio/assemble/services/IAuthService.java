package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.IServiceHandlerWithArgs;

/**
 * Created by Master on 10/07/2016.
 */
public interface IAuthService {
    void initCheckAccess(final IServiceHandler listener);

    void startCheckAccess();

    void stopCheckAccess();

    void login(String email, String password, final IServiceHandlerWithArgs<String> listener);

    void create(String email, String password, final IServiceHandlerWithArgs<String> listener);

    void signOut();

    String getValue(String field);

    String getID();
}
