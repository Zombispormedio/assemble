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

    void hideImageProfile();
    void showImageProfile();

    void setUsername(String name);
    void setLocation(String location);
    void setBio(String bio);
    void setBirthDate(String birth);

    void goToUpdateProfile();

}
