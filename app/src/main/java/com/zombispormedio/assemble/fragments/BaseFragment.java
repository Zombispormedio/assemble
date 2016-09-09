package com.zombispormedio.assemble.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class BaseFragment  extends Fragment {
    private Unbinder unbinder;

    protected void bind(Object ctx, Activity view){
        unbinder= ButterKnife.bind(ctx, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
