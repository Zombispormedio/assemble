package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
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

    public FriendsController(IFriendsView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
        userResource = ResourceFactory.createUser();
    }

    public void onNewFriend() {
        ctx.goToNewFriend();
    }

    @Override
    public void onCreate() {
        fillFriends();
    }

    private void fillFriends() {



        if(user.getFriendsCount()>0){
            ctx.bindFriends(user.getFriends());
        }else{
            ctx.loading();
        }

        userResource.getFriends(new IServiceHandler<ArrayList<FriendProfile>, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                ctx.unloading();
            }

            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                ctx.unloading();
                user.setFriends(result);
                ctx.bindFriends(result);
            }
        });




    }


}
