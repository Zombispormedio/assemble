package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IMeetingService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class MeetingLoader implements ILoader {

    private IMeetingService apiService;

    private IStorageService<Meeting> storageService;

    public MeetingLoader(IMeetingService apiService,
            IStorageService<Meeting> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getAll(new ServiceHandler<ArrayList<Meeting>, Error>() {
            @Override
            public void onSuccess(ArrayList<Meeting> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }
        });
    }
}
