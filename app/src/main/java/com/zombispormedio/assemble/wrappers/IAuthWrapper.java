package com.zombispormedio.assemble.wrappers;

import com.zombispormedio.assemble.listeners.IListener;
import com.zombispormedio.assemble.listeners.IListenerWithArgs;

/**
 * Created by Master on 10/07/2016.
 */
public interface IAuthWrapper{
    void initCheckAccess(final IListener listener);

    void startCheckAccess();

    void stopCheckAccess();

    void login(String email, String password, final IListenerWithArgs<String> listener);

    void create(String email, String password, final IListenerWithArgs<String> listener);

    void signOut();

    String getValue(String field);
}
