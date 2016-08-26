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

    private CurrentUser user;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        userResource = ResourceFactory.createUser();
        user = CurrentUser.getInstance();
    }

    @Override
    public void onCreate() {
        loadData();
        setDrawerTitle();
    }


    public void onDrawerOpened() {

    }

    private void setDrawerTitle() {
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

    public void onSettingsMenuItem() {
        ctx.goToSettings();
    }

    public void onProfileMenuItem() {
        ctx.goToProfile();
    }

    public void onHelpMenuItem() {
        ctx.goToHelp();
    }

    public void onFriendsMenuItem() {
        ctx.goToFriends();
    }


    private void loadData() {
        getProfile();
    }

    private void getProfile() {

        userResource.getProfile(new IServiceHandler<UserProfile, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(UserProfile result) {
                user.setProfile(result);
                setDrawerTitle();
            }
        });
    }


    @Override
    public void onDestroy() {
        ctx = null;
    }


}
