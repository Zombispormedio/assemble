package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends AbstractController {

    private IHomeView ctx;

    private UserResource userResource;

    private ProfileHandler profileHandler;

    private CurrentUser user;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        userResource = ResourceFactory.createUser();
        user = CurrentUser.getInstance();
        profileHandler = new ProfileHandler();

        userResource.getProfile(profileHandler);
    }

    public void onDrawerOpened() {
        DrawerTitle();
    }

    private void DrawerTitle() {
        UserProfile profile = user.getProfile();
        String title = "";
        if (!profile.username.isEmpty()) {
            title = profile.username;

        } else {
            if (!profile.email.isEmpty()) {
                title = profile.email;
            }
        }
        if (ctx != null) {
            ctx.setDrawerTitle(title);
        }
    }

    public void onSettingsMenuItemClick() {
        ctx.goToSettings();
    }


    @Override
    public void onDestroy() {
        ctx = null;
    }


    public void onProfileMenuItemClick() {
        ctx.goToProfile();
    }

    private class ProfileHandler implements IServiceHandler<UserProfile, Error> {

        @Override
        public void onError(Error error) {

        }

        @Override
        public void onSuccess(UserProfile result) {
            user.setProfile(result);
            DrawerTitle();
        }
    }

}
