package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.views.ICreateMeetingView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateMeetingController extends Controller {

    private ICreateMeetingView ctx;

    public CreateMeetingController(ICreateMeetingView ctx) {
        super(ctx);
        this.ctx = ctx;
    }
}
