package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.activities.IHelpView;

import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public class HelpController extends Controller {


    private IHelpView ctx;

    public HelpController(IHelpView ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
