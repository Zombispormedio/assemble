package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.services.interfaces.IFriendService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.network.Error;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestLoader implements ILoader {

    private final IFriendService apiService;

    private final IStorageService<FriendRequestProfile> storageService;

    public FriendRequestLoader(IFriendService apiService,
            IStorageService<FriendRequestProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(@NonNull final SuccessHandler handler) {
        apiService.getFriendRequests(new ServiceHandler<ArrayList<FriendRequestProfile>, Error>() {
            @Override
            public void onSuccess(ArrayList<FriendRequestProfile> result) {
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
