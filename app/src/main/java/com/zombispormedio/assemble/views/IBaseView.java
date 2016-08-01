package com.zombispormedio.assemble.views;

/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public interface IBaseView {
    String getAuthToken();
    void setAuthToken(String token);
    void clearAuthToken();
    void showAlert(String msg);

}
