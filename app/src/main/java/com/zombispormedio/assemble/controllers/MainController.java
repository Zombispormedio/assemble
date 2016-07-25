package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.IServiceHandler2;
import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.resources.UserResources;
import com.zombispormedio.assemble.views.IMainView;

/**
 * Created by Master on 08/07/2016.
 */
public class MainController implements IBaseController {



    private IMainView ctx;
    private UserResources user;

    public MainController(IMainView ctx) {
        this.ctx=ctx;
        user= ModelBuilder.createUser();

    }

    public void checkAccess() {

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
