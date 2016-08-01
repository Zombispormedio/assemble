package com.zombispormedio.assemble.views;


import com.zombispormedio.assemble.handlers.ISuccessHandler;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileView extends IBaseView {

    void setProfileImage(String url, final ISuccessHandler handler);
    void loadDefaultImage(final ISuccessHandler handler);

    void hideImageForm();
    void showImageForm();
    void hideProgressImage();
    void showProgressImage();

}
