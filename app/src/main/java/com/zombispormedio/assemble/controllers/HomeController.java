package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.IHomeView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends AbstractController {


    private IHomeView ctx;

    private final UserResource userResource;

    private final TeamResource teamResource;

    private final MeetingResource meetingResource;

    private CurrentUser user;

    private boolean isTeamsReady;

    private boolean isProfileReady;

    private boolean isMeetingsReady;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;

        userResource = ResourceFactory.createUserResource();
        teamResource = ResourceFactory.createTeamResource();
        meetingResource = ResourceFactory.createMeetingResource();

        user = CurrentUser.getInstance();

        isProfileReady = false;
        isTeamsReady = false;
        isMeetingsReady = false;
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

        if (user.isProfileEmpty()) {
            loading();
        }

        getProfile();
        getTeams();
        getMeetings();

    }


    private void getProfile() {

        userResource.getProfile(new IServiceHandler<UserProfile, Error>() {
            @Override
            public void onError(Error error) {
                readyProfile();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(UserProfile result) {
                user.setProfile(result);
                readyProfile();
                setDrawerTitle();
            }
        });
    }

    private void getTeams() {

        teamResource.getAll(new IServiceHandler<ArrayList<Team>, Error>() {
            @Override
            public void onError(Error error) {
                readyTeams();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Team> result) {
                user.setTeams(result);
                readyTeams();
            }
        });
    }

    private void getMeetings() {
        meetingResource.getAll(new IServiceHandler<ArrayList<Meeting>, Error>() {
            @Override
            public void onError(Error error) {
                readyMeetings();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<Meeting> result) {
                user.setMeetings(result);
                readyMeetings();
            }
        });
    }


    private boolean isReady() {
        return isProfileReady && isTeamsReady && isMeetingsReady;
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


    @Override
    public void onDestroy() {
        ctx = null;
    }


}
