package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.editors.MeetingEditor;
import com.zombispormedio.assemble.models.services.interfaces.IMeetingService;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.FileBody;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.Result;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingAPIService implements IMeetingService {

    @NonNull
    private final APIConfiguration api;

    public MeetingAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void getAll(final IServiceHandler<ArrayList<Meeting>, Error> handler) {
        api.RestWithAuth("/meetings")
                .handler(DeferUtils.deferMeetings(handler))
                .get();
    }

    @Override
    public void create(MeetingEditor meeting, IServiceHandler<Meeting, Error> handler) {
        api.RestWithAuth("/meeting")
                .handler(DeferUtils.deferMeeting(handler))
                .post(JsonBinder.fromEditMeeting(meeting));
    }

    @Override
    public void uploadImage(int meetingId, @NonNull File file, IServiceHandler<Meeting, Error> handler) {
        api.RestWithAuth("/meeting/:id/image")
                .params("id", meetingId)
                .handler(DeferUtils.deferMeeting(handler))
                .patch(new FileBody(file, "image/*", "image", file.getName()));
    }

    @Override
    public void bookmark(int meetingId, IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/meeting/:id/bookmark")
                .params("id", meetingId)
                .handler(DeferUtils.defer(handler))
                .put();
    }


}
