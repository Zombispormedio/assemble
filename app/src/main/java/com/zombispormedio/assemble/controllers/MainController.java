package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.activities.IMainView;

/**
 * Created by Master on 08/07/2016.
 */
public class MainController implements   IBaseListener<String>, IBaseController {
    private IMainView ctx;
    public MainController(IMainView ctx) {
        this.ctx=ctx;
    }

    @Override
    public void onError(String... args) {

    }

    @Override
    public void onSuccess(String... args) {

    }


    @Override
    public void onDestroy() {

    }
}
