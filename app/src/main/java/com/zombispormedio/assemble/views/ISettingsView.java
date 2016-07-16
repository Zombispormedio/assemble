package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.listeners.IListener;

/**
 * Created by Master on 16/07/2016.
 */
public interface ISettingsView {

    void showConfirmSignOutDialog( IListener listener);

    void goToLogin();
}
