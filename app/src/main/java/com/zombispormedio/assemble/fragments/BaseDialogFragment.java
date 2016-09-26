package com.zombispormedio.assemble.fragments;

import com.zombispormedio.assemble.views.IApplicationView;
import com.zombispormedio.assemble.views.IBaseView;
import com.zombispormedio.assemble.views.IFragmentView;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class BaseDialogFragment extends DialogFragment implements IFragmentView {

    private IBaseView view;

    protected void bindView(Activity view){
        this.view= (IBaseView) view;
    }


    @Override
    public IApplicationView getParent() {
        return view;
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }
}
