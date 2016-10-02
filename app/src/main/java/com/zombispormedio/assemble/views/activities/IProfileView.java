package com.zombispormedio.assemble.views.activities;


import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.views.activities.IBaseProfileView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileView extends IBaseProfileView {

    void setProfileImage(String url, String letter, final ISuccessHandler handler);

    void loadLetterImage(String letter, final ISuccessHandler handler);

    void hideImageForm();

    void showImageForm();

    void hideImageProgressDialog();

    void showImageProgressDialog();

    void hideImageProgressBar();

    void showImageProgressBar();

    void goToUpdateProfile();

}
