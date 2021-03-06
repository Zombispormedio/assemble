package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;
import com.zombispormedio.assemble.views.fragments.IMeetingsView;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsController extends Controller {


    private IMeetingsView ctx;

    private final MeetingResource meetingResource;

    private final MeetingSubscription meetingSubscription;

    @NonNull
    private final MeetingSubscriber meetingSubscriber;

    private boolean refreshing;

    public MeetingsController(@NonNull IMeetingsView ctx) {
        super(ctx.getParent());
        this.ctx = ctx;
        meetingResource = getResourceComponent().provideMeetingResource();
        meetingSubscription = getResourceComponent().provideMeetingSubscription();
        meetingSubscriber = new MeetingSubscriber();
        meetingSubscription.addSubscriber(meetingSubscriber);
        refreshing = false;
    }

    @Override
    public void onCreate() {
        renderMeetings();
    }


    private void renderMeetings() {
        ArrayList<Meeting> meetings = meetingResource.getAll();
        ctx.bindMeetings(meetings);
    }


    public void onClickOneTeam(int position, Meeting meeting) {

    }

    public void onRefresh() {
        refreshing = true;
        meetingSubscription.load();
    }

    public void onBookmark(int i, @NonNull Meeting meeting) {

        meetingResource.bookmark(meeting.id, new ServiceHandler<Result, Error>() {
            @Override
            public void onSuccess(Result result) {
                renderMeetings();
            }
        });
    }

    private class MeetingSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            renderMeetings();
            finishRefresh();
        }

        @Override
        public void notifyFail() {
            finishRefresh();
        }
    }

    private void finishRefresh() {
        if (refreshing) {
            refreshing = false;
            ctx.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        meetingSubscription.removeSubscriber(meetingSubscriber);
        ctx = null;
    }
}
