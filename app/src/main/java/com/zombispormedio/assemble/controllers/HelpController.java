package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IHelpView;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public class HelpController extends AbstractController {

    private IHelpView ctx;

    public HelpController(IHelpView ctx) {
        this.ctx = ctx;
    }
}
