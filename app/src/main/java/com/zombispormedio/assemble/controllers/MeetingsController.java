package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.IMeetingsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsController extends AbstractController {

    private IMeetingsView ctx;

    private MeetingResource meetingResource;

    private CurrentUser user;

    public MeetingsController(IMeetingsView ctx) {
        this.ctx = ctx;
        user=CurrentUser.getInstance();
        meetingResource= ResourceFactory.createMeetingResource();
    }

    @Override
    public void onCreate() {
        setupMeetings();
    }

    private void setupMeetings() {
        if(user.getMeetingsCount()>0){
            ctx.bindMeetings(user.getMeetings());
        }

        getMeetings();

    }

    private void getMeetings() {
        meetingResource.getAll(new IServiceHandler<ArrayList<Meeting>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Meeting> result) {
                user.setMeetings(result);
                ctx.bindMeetings(result);
            }
        });
    }

    public IOnClickItemListHandler<Meeting> getOnClickOneTeam() {
        return new IOnClickItemListHandler<Meeting>() {
            @Override
            public void onClick(int position, Meeting data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }


}
