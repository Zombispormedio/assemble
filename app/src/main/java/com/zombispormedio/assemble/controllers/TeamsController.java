package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.ITeamsView;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsController extends AbstractController {

    private ITeamsView ctx;

    public TeamsController(ITeamsView ctx) {
        this.ctx = ctx;
    }
}
