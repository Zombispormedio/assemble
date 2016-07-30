package com.zombispormedio.assemble.views;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IHomeView extends IBaseView  {
    void goToLogin();

    void goToProfile();

    void goToSettings();

    void setDrawerTitle(String hello);
}
