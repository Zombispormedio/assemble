package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.handlers.ISuccessHandler;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public interface IUpdateProfileView extends IBaseProfileView {

    String getUsername();

    String getBio();

    String getLocation();

    void goToUpdateBirthdate(String... args);

    void close();

    void openProgressDialog();

    void closeProgressDialog();

    void showRejectChangesDialog( ISuccessHandler listener);
}
