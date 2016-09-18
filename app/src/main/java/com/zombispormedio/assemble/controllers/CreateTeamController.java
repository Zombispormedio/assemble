package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.ICreateTeamView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateTeamController extends Controller {

    private ICreateTeamView ctx;

    public CreateTeamController(ICreateTeamView ctx) {
        super(ctx);
        this.ctx = ctx;
    }
}
