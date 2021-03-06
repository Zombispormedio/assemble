package com.zombispormedio.assemble.views.activities;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IHomeView extends IBaseView {

    void goToLogin();

    void goToProfile();

    void goToSettings();

    void goToFriends();

    void goToHelp();

    void hideProgressDialog();

    void showProgressDialog();

    void showOverlay();

    void hideOverlay();

    void bindUsernameLabel(String username);

    void bindEmailLabel(String email);

    void bindAvatar(String path, String letter);

    void showBackgroundLoading();

    void hideBackgroundLoading();

    boolean isLoaded();

    void notifyLoaded();
}
