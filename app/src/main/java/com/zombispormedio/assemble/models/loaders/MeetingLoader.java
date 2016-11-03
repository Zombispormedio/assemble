package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.services.interfaces.IMeetingService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.network.Error;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class MeetingLoader implements ILoader {

    private final IMeetingService apiService;

    private final IStorageService<Meeting> storageService;

    public MeetingLoader(IMeetingService apiService,
            IStorageService<Meeting> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(@NonNull final SuccessHandler handler) {
        apiService.getAll(new ServiceHandler<ArrayList<Meeting>, Error>() {
            @Override
            public void onSuccess(ArrayList<Meeting> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }

            @Override
            public void onError(Error error) {
                handler.onError();
            }

            @Override
            public void onNotConnected() {
                handler.onError();
            }
        });
    }
}
