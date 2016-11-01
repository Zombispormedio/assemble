package com.zombispormedio.assemble.views.activities;

/**
 * Created by Xavier Serrano on 09/07/2016.
 */
public interface ILoginView extends IBaseView {

    void goHome();

    void goToRegister();

    void showProgressBar();

    void hideProgressBar();

    void showForm();

    void hideForm();

    String getEmail();

    String getPassword();

    void showSuccessfulLogin();

    boolean validate();

    void showFailValidation();

}
