package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IProfileView;

/**
 * Created by Master on 10/07/2016.
 */
public class ProfileController implements IBaseController {

    private IProfileView ctx;

    public ProfileController(IProfileView ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
