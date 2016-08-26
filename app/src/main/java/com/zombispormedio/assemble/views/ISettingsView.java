package com.zombispormedio.assemble.views;


import com.zombispormedio.assemble.handlers.ISuccessHandler;

/**
 * Created by Xavier Serrano on 16/07/2016.
 */
public interface ISettingsView extends IBaseView {

    void showConfirmSignOutDialog(ISuccessHandler listener);

    void goToLogin();

}
