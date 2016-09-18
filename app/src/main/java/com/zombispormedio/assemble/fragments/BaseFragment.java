package com.zombispormedio.assemble.fragments;

import com.zombispormedio.assemble.views.IApplicationView;
import com.zombispormedio.assemble.views.IBaseView;
import com.zombispormedio.assemble.views.IFragmentView;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class BaseFragment  extends Fragment implements IFragmentView {

    private IBaseView view;

    protected void bindView(Object ctx, Activity view){
        ButterKnife.bind(ctx, view);
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
