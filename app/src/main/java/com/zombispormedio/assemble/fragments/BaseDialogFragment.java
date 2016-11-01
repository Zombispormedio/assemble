package com.zombispormedio.assemble.fragments;

import com.zombispormedio.assemble.views.activities.IBaseView;
import com.zombispormedio.assemble.views.fragments.IFragmentView;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class BaseDialogFragment extends DialogFragment implements IFragmentView {

    private IBaseView view;

    protected void bindView(Activity view) {
        this.view = (IBaseView) view;
    }


    @Override
    public IBaseView getParent() {
        return view;
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }
}
