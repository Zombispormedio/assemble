package com.zombispormedio.assemble.views;

/**
 * Created by Master on 09/07/2016.
 */
public interface IRegisterView {

    void showProgressBar();

    void hideProgressBar();

    void showForm();

    void hideForm();

    String getEmail();

    String getPassword();

    String getRepPassword();

    void goMain();

    void showEmptyEmail();

    void showEmptyPassword();

    void showEmptyRepPassword();

    void showNotEqualsBothPassword();

    void showAlert(String msg);
}
