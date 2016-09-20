package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.MeetingsResponse;
import com.zombispormedio.assemble.services.interfaces.IMeetingService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingAPIService implements IMeetingService {
    private APIConfiguration api;

    public MeetingAPIService() {
        api=APIConfiguration.getInstance();
    }

    @Override
    public void getAll(final IServiceHandler<ArrayList<Meeting>, Error> handler) {
        api.RestWithAuth("/meetings")
                .handler(DeferUtils.deferMeetings(handler))
                .get();
    }



}
