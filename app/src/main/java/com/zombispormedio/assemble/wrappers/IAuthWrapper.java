package com.zombispormedio.assemble.wrappers;

import com.zombispormedio.assemble.controllers.IBaseListener;

/**
 * Created by Master on 10/07/2016.
 */
public interface IAuthWrapper{
    void initCheckAccess(final IBaseListener<Integer> listener);

    void startCheckAccess();

    void stopCheckAccess();

    void login(String email, String password, final IBaseListener<String> listener);

    void create(String email, String password, final IBaseListener<String> listener);

    void signOut();

    String getValue(String field);
}
