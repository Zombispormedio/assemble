package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.editors.MeetingEditor;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public interface IMeetingService {

    void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler);

    void create(MeetingEditor meeting, IServiceHandler<Meeting, Error> handler);

    void uploadImage(int meetingId, File file, IServiceHandler<Meeting, Error> handler);

    void bookmark(int meetingId, IServiceHandler<Result, Error> handler);
}
