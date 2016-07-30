package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.JSONBinder;
import com.zombispormedio.assemble.rest.responses.ProfileResponse;
import com.zombispormedio.assemble.services.IPersistenceService;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 27/07/2016.
 */
public class UserAPIService implements IPersistenceService<UserProfile> {
    private APIConfiguration api;

    public UserAPIService() {
        api=APIConfiguration.getInstance();
    }

    @Override
    public void retrieve(final IServiceHandler<UserProfile, Error> handler) {
        api.RestWithAuth("/profile")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            ProfileResponse res= JSONBinder.toProfileResponse(args[0]);
                            if(res.success){
                                handler.onSuccess(res.result);
                            }else{
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .get();
    }
}
