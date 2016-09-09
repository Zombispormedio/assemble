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

    protected void bindView(Object ctx, Activity view){
        ButterKnife.bind(ctx, view);
    }

}
