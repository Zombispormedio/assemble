package com.zombispormedio.assemble.views.activities;


import com.zombispormedio.assemble.utils.ImageUtils;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileView extends IBaseProfileView {

    void setProfileImage(ImageUtils.ImageBuilder buidler);

    void hideImageProgressDialog();

    void showImageProgressDialog();

    void goToUpdateProfile();

}
