package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Master on 10/07/2016.
 */
public class HomeController implements IBaseController {

    private IHomeView ctx;

    public HomeController(IHomeView ctx) {
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
