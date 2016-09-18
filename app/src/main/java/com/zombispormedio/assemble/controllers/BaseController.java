package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.views.IApplicationView;
import com.zombispormedio.assemble.views.IBaseView;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class BaseController extends AbstractController {
    private IApplicationView app;

    public BaseController(IApplicationView app) {
        this.app = app;
    }

    protected ResourceComponent getResourceComponent(){
        return app.getResourceComponent();
    }
}
