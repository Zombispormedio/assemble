package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.views.IApplicationView;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public interface IFragmentView {

    IApplicationView getParent();

    void showAlert(String msg);
}
