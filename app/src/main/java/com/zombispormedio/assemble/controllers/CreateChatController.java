package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.ICreateChatView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateChatController extends Controller {

    private ICreateChatView ctx;

    public CreateChatController(ICreateChatView ctx) {
        super(ctx);
        this.ctx = ctx;
    }
}
