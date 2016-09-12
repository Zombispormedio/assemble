package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IMeetingService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingResource {

    private IMeetingService persistence;

    public MeetingResource(IMeetingService persistence) {
        this.persistence = persistence;
    }

    public void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler){
        persistence.getAll(handler);
    }
}
