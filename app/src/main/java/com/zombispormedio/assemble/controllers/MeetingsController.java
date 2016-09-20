package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.views.IMeetingsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsController extends Controller {

    private IMeetingsView ctx;

    private MeetingResource meetingResource;

    private MeetingSubscription meetingSubscription;

    private MeetingSubscriber meetingSubscriber;

    private boolean refreshing;

    public MeetingsController(IMeetingsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        meetingResource = getResourceComponent().provideMeetingResource();
        meetingSubscription = getResourceComponent().provideMeetingSubscription();
        meetingSubscriber = new MeetingSubscriber();
        meetingSubscription.addSubscriber(meetingSubscriber);
        refreshing=false;
    }

    @Override
    public void onCreate() {
        setupMeetings();
    }

    private void setupMeetings() {
        bindMeetings();

        meetingSubscription.load();

    }

    public void bindMeetings() {
        ArrayList<Meeting> meetings = meetingResource.getAll();

        if (meetings.size() > 0) {
            ctx.bindMeetings(meetings);
        }
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

    public void onRefresh() {
        refreshing=true;
        meetingSubscription.load();
    }

    private class MeetingSubscriber extends Subscriber {
        @Override
        public void notifyChange() {
            bindMeetings();
            finishRefresh();
        }
    }

    private void finishRefresh(){
        if(refreshing){
            refreshing=false;
            ctx.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        meetingSubscription.removeSubscriber(meetingSubscriber);
    }
}
