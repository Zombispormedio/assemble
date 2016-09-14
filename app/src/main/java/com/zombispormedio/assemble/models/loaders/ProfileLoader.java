package com.zombispormedio.assemble.models.loaders;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IProfileService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import android.util.Log;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ProfileLoader implements ILoader {

    private IProfileService apiService;

    private IStorageService<UserProfile> storageService;

    public ProfileLoader(IProfileService apiService,
            IStorageService<UserProfile> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {

        apiService.retrieve(new ServiceHandler<UserProfile, Error>() {
            @Override
            public void onSuccess(UserProfile result) {
                Logger.d(result.full_avatar_url);
                storageService.createOrUpdate(result);
                handler.onSuccess();
            }
        });

    }
}
