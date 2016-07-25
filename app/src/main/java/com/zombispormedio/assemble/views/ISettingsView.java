package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.handlers.IServiceHandler2;

/**
 * Created by Master on 16/07/2016.
 */
public interface ISettingsView {

    void showConfirmSignOutDialog( IServiceHandler2 listener);

    void goToLogin();
}
