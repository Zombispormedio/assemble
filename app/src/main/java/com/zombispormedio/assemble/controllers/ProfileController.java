package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IProfileView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ProfileController implements IBaseController {

    private IProfileView ctx;
    private UserProfile userProfile;
    private UserResource userResource;

    public ProfileController(IProfileView ctx) {
        this.ctx = ctx;
        userProfile =null;
        ProfileHandler profileHandler = new ProfileHandler();
        userResource = ResourceFactory.createUser();
        userResource.getProfile(profileHandler);
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    private class ProfileHandler implements IServiceHandler<UserProfile, Error> {
        @Override
        public void onError(Error error) {

        }

        @Override
        public void onSuccess(UserProfile result) {
            userProfile =result;
            if(ctx!=null){
                ctx.setProfileImage(userProfile.full_avatar_url);
            }

        }
    }
}
