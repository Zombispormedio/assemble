package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.editors.EditMeeting;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.models.services.interfaces.IMeetingService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingResource extends ConceptResource<Meeting>{

    private final IMeetingService persistence;

    @Inject
    public MeetingResource(IMeetingService persistence,
            IStorageService<Meeting> storage) {
        super(storage);
        this.persistence = persistence;

    }

    public void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler){
        persistence.getAll(handler);
    }


    public void create(EditMeeting editMeeting, final IServiceHandler<Meeting, Error> handler){

        persistence.create(editMeeting, new ServiceHandler<Meeting, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Meeting result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });

    }

    public void uploadImage(int meetingId, String path, final IServiceHandler<Meeting, Error> handler){
        persistence.uploadImage(meetingId, new File(path), new ServiceHandler<Meeting, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Meeting result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });
    }

}
