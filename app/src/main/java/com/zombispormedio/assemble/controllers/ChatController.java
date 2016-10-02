package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.IApplicationView;
import com.zombispormedio.assemble.views.activities.IChatView;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public class ChatController extends Controller {

    private IChatView ctx;

    public ChatController(IChatView ctx) {
        super(ctx);
        this.ctx=ctx;
    }
}
