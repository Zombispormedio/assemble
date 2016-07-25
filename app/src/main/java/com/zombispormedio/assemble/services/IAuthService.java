package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.handlers.IServiceHandler2;


/**
 * Created by Master on 10/07/2016.
 */
public interface IAuthService {
    void checkAccess(final IServiceHandler2 listener);


    void login(String email, String password, final IServiceHandler2<String, String> listener);

    void create(String email, String password, final IServiceHandler2<String, String> listener);

    void signOut();

    String getValue(String field);

    String getID();
}
