package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.models.components.ResourceComponent;

/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public interface IBaseView {

    ResourceComponent getResourceComponent();

    String getAuthToken();

    void setAuthToken(String token);

    void clearAuthToken();

    void showAlert(String msg);

}
