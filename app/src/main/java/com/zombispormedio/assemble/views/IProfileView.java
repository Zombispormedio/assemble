package com.zombispormedio.assemble.views;


import com.zombispormedio.assemble.handlers.ISuccessHandler;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileView extends IBaseProfileView{

    void setProfileImage(String url, final ISuccessHandler handler);
    void loadLetterImage(String letter,final ISuccessHandler handler);
    void loadDefaultImage(final ISuccessHandler handler);

    void hideImageForm();
    void showImageForm();
    void hideImageProgressDialog();
    void showImageProgressDialog();

    void hideImageProgressBar();
    void showImageProgressBar();

    void goToUpdateProfile();

}
