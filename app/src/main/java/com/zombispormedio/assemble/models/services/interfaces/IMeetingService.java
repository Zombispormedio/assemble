package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.editors.EditMeeting;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public interface IMeetingService {

    void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler);

    void create(EditMeeting meeting, IServiceHandler<Meeting, Error> handler);

    void uploadImage(int meetingId, File file, IServiceHandler<Meeting, Error> handler);
}
