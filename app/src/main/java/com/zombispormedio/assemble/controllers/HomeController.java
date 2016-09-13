package com.zombispormedio.assemble.controllers;


import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.IHomeView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends AbstractController {


    private IHomeView ctx;


    private final ProfileResource profileResource;

    private ProfileSubscription profileSubscription;

    private CurrentUser user;

    private boolean isTeamsReady;

    private boolean isProfileReady;

    private boolean isMeetingsReady;

    private boolean isChatsReady;

    public HomeController(IHomeView ctx) {
        this.ctx = ctx;


        profileResource= ResourceFactory.createProfileResource();

        user = CurrentUser.getInstance();

        profileSubscription= user.getProfileSubscription();

        isProfileReady = false;
        isTeamsReady = false;
        isMeetingsReady = false;
        isChatsReady=false;
    }

    @Override
    public void onCreate() {
        loadData();
    }


    public void onDrawerOpened() {
        setDrawerTitle();
    }

    private void setDrawerTitle() {
        UserProfile profile = profileResource.getProfile();
       Logger.d(profile.username);

        String title = "";

        if (!profile.username.isEmpty()) {
            title = profile.username;

        } else {
            if (!profile.email.isEmpty()) {
                title = profile.email;
            }
        }
        if (ctx != null && !title.isEmpty()) {
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

        profileSubscription.addSubscriber(new ProfileSubscriber());
        profileSubscription.load();

    }




    private boolean isReady() {
        return isProfileReady && isTeamsReady && isMeetingsReady && isChatsReady;
    }

    private void loading() {
        uncheckAll();
        ctx.showProgressDialog();
    }


    private void ready() {
        if (isReady()) {
            ctx.hideProgressDialog();
            uncheckAll();
        }
    }

    private void uncheckAll() {
        isProfileReady = false;
        isTeamsReady = false;
        isMeetingsReady = false;
        isChatsReady=false;
    }

    private void readyProfile() {
        isProfileReady = true;
        ready();
    }

    private void readyTeams() {
        isTeamsReady = true;
        ready();
    }


    private void readyMeetings() {
        isMeetingsReady = true;
        ready();
    }

    private void readyChats() {
        isChatsReady = true;
        ready();
    }



    @Override
    public void onDestroy() {
        ctx = null;
    }

    private class ProfileSubscriber extends Subscriber{

        @Override
        public void notifyChange() {
            ctx.showAlert("Profile loaded");
            Logger.d("Yes, profile loaded. You are subscribe");
        }
    }


}
