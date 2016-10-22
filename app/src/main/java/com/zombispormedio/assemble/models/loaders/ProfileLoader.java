package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.models.services.interfaces.IProfileService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ProfileLoader implements ILoader {

    private final IProfileService apiService;

    private final IStorageService<UserProfile> storageService;

    public ProfileLoader(IProfileService apiService,
            IStorageService<UserProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final SuccessHandler handler) {

        apiService.retrieve(new ServiceHandler<UserProfile, Error>() {
            @Override
            public void onSuccess(UserProfile result) {
                storageService.createOrUpdate(result);
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
