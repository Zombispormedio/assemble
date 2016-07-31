package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.handlers.IPromiseHandler;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileView extends IBaseView {

    void setProfileImage(String url, final IPromiseHandler handler);
    void loadDefaultImage(final IPromiseHandler handler);

    void hideImageForm();
    void showImageForm();
    void hideProgressImage();
    void showProgressImage();

}
