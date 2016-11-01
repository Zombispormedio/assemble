package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.views.activities.IBaseView;

import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
public class Controller extends AbstractController {

    @Nullable
    private IBaseView app;

    public Controller(IBaseView app) {
        this.app = app;
    }

    @Nullable
    protected ResourceComponent getResourceComponent() {
        return app != null ? app.getResourceComponent() : null;
    }

    @Override
    public void onDestroy() {
        app = null;
    }
}
