package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.ICreateChatView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateChatController extends AbstractController {

    private ICreateChatView ctx;

    public CreateChatController(ICreateChatView ctx) {
        this.ctx = ctx;
    }
}
