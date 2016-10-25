package com.zombispormedio.assemble.views.fragments;


import com.zombispormedio.assemble.views.activities.IBaseView;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public interface IFragmentView {

    IBaseView getParent();

    void showAlert(String msg);
}
