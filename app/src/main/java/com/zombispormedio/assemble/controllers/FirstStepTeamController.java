package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IFirstStepTeamView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class FirstStepTeamController extends Controller {

    private IFirstStepTeamView ctx;

    public FirstStepTeamController(IFirstStepTeamView ctx) {
        super(ctx);
        this.ctx = ctx;
    }
}
