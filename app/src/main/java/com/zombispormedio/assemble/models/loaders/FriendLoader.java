package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IFriendService;
import com.zombispormedio.assemble.services.interfaces.IProfileService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendLoader implements ILoader {

    private IFriendService apiService;

    private IStorageService<FriendProfile> storageService;

    public FriendLoader(IFriendService apiService,
            IStorageService<FriendProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getFriends(new ServiceHandler<ArrayList<FriendProfile>, Error>() {
            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }
        });
    }



}
