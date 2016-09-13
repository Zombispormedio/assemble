package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IProfileService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestLoader implements ILoader {

    private IProfileService apiService;

    private IStorageService<FriendRequestProfile> storageService;

    public FriendRequestLoader(IProfileService apiService,
            IStorageService<FriendRequestProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getFriendRequests(new ServiceHandler<ArrayList<FriendRequestProfile>, Error>() {
            @Override
            public void onSuccess(ArrayList<FriendRequestProfile> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }
        });
    }
}
