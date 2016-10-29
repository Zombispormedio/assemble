package com.zombispormedio.assemble.fragments;

import com.zombispormedio.assemble.views.activities.IBaseView;
import com.zombispormedio.assemble.views.fragments.IFragmentView;

import android.app.Activity;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

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
    public IBaseView getParent() {
        return view;
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }
}
