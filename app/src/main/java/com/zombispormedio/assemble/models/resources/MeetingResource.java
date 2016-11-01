package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.editors.MeetingEditor;
import com.zombispormedio.assemble.models.services.interfaces.IMeetingService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.models.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingResource extends AbstractResource<Meeting> {

    private final IMeetingService persistence;

    @Inject
    public MeetingResource(IMeetingService persistence,
            IStorageService<Meeting> storage) {
        super(storage);
        this.persistence = persistence;

    }

    public void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler) {
        persistence.getAll(handler);
    }


    public void create(MeetingEditor meetingEditor, final IServiceHandler<Meeting, Error> handler) {

        persistence.create(meetingEditor, new ServiceHandler<Meeting, Error>(handler) {
            @Override
            public void onSuccess(Meeting result) {
                storage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });

    }

    public void uploadImage(int meetingId, @NonNull String path, final IServiceHandler<Meeting, Error> handler) {
        persistence.uploadImage(meetingId, new File(path), new ServiceHandler<Meeting, Error>() {
            @Override
            public void onSuccess(Meeting result) {
                storage.createOrUpdate(result);
                super.onSuccess(result);
            }
        });
    }

    public void bookmark(int meetingId, @NonNull final IServiceHandler<Result, Error> handler) {
        persistence.bookmark(meetingId, new ServiceHandler<Result, Error>() {
            @Override
            public void onSuccess(Result result) {
                getMeetingStorage().bookmark(meetingId);
                handler.onSuccess(result);
            }
        });
    }

    @NonNull
    private MeetingStorageService getMeetingStorage() {
        return (MeetingStorageService) storage;
    }

}
