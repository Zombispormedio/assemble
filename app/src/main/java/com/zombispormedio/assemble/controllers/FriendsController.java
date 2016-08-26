package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IFriendsView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public class FriendsController extends AbstractController {

    private IFriendsView ctx;

    private CurrentUser user;

    private UserResource userResource;

    private boolean isLoading;

    private boolean isFriendsReady;

    private boolean isRequestsReady;

    public FriendsController(IFriendsView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
        isFriendsReady = isRequestsReady = false;
        isLoading = false;

    }

    public void onNewFriend() {
        ctx.goToNewFriend();
    }

    @Override
    public void onCreate() {
        setupFriendsAndRequests();
    }

    private void setupFriendsAndRequests() {

        boolean haveFriends = user.getFriendsCount() > 0;
        boolean haveRequests = user.getFriendRequestsCount() > 0;

        if (haveFriends || haveRequests) {
            if (haveFriends) {
                ctx.bindFriends(user.getFriends());
            }
            if (haveRequests) {
                ctx.bindFriendRequests(user.getFriendRequests());
            }
        } else {
            loadingTime();
        }

        getFriends();
        getRequests();
    }

    private void getFriends() {
        isFriendsReady = false;

        userResource.getFriends(new IServiceHandler<ArrayList<FriendProfile>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                readyFriends();
            }

            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                user.setFriends(result);
                ctx.bindFriends(result);
                readyFriends();
            }
        });
    }


    private void getRequests() {
        isRequestsReady = false;
        userResource.getFriendRequests(new IServiceHandler<ArrayList<FriendRequestProfile>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                readyRequests();
            }

            @Override
            public void onSuccess(ArrayList<FriendRequestProfile> result) {
                user.setFriendRequests(result);
                ctx.bindFriendRequests(result);
                readyRequests();
            }
        });
    }


    private void loadingTime() {
        if (!isLoading) {
            ctx.loading();
            hideLists();
            isLoading = true;
        }
    }

    private void hideLists() {
        ctx.hideFriendsList();
        ctx.hideRequestsList();
    }

    private void noLoadTime() {
        if (isLoading && isReady()) {
            ctx.unloading();
            showLists();
            isLoading = false;
        }
    }

    private void showLists() {
        ctx.showFriendsList();
        ctx.showRequestsList();
    }

    private void readyFriends() {
        isFriendsReady = true;
        noLoadTime();
    }

    private void readyRequests() {
        isRequestsReady = true;
        noLoadTime();
    }

    private boolean isReady() {
        return isFriendsReady && isRequestsReady;
    }


    public IOnClickItemListHandler<FriendProfile> getOnClickOneFriend() {
        return new IOnClickItemListHandler<FriendProfile>() {
            @Override
            public void onClick(int position, FriendProfile data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }

    public IOnClickItemListHandler<FriendRequestProfile> getOnClickOneRequest() {
        return new IOnClickItemListHandler<FriendRequestProfile>() {
            @Override
            public void onClick(int position, FriendRequestProfile data) {
                Logger.d(position);
                Logger.d(data);
            }
        };
    }

}
