package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IProfileService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendLoader implements ILoader {

    private IProfileService apiService;

    private IStorageService<FriendProfile> storageService;

    public FriendLoader(IProfileService apiService,
            IStorageService<FriendProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getFriends(new IServiceHandler<ArrayList<FriendProfile>, Error>() {
            @Override
            public void onError(Error error) {

            }

            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                storageService.createOrUpdateAll(result);
                handler.onSuccess();
            }
        });
    }



}
