package com.zombispormedio.assemble.views;

/**
 * Created by Master on 09/07/2016.
 */
public interface ILoginView {

    void goHome();
    
    void goToRegister();

     void showProgressBar();

    void hideProgressBar();

    void showForm();

    void hideForm();

    String getEmail();

    String getPassword();

    void showEmptyEmail();

    void showEmptyPassword();

    void showAlert(String msg);
    void showSuccessfulLogin();

}
