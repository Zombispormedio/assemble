package com.zombispormedio.assemble.models.resources;



import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.services.IAuthService;
import com.zombispormedio.assemble.services.IProfileService;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Xavier Serrano on 08/07/2016.
 */
public class UserResource {

    private IAuthService auth;
    private IProfileService persistence;

    public UserResource(IAuthService auth, IProfileService persistence) {
        this.auth=auth;
        this.persistence=persistence;
    }

    public void checkAccess(IServiceHandler<Result,Error> listener){
        auth.checkAccess(listener);
    }


    public void login(String email, String password, IServiceHandler<Result, Error> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IServiceHandler<Result, Error> listener) {
        auth.register(email, password, listener);
    }


    public void signOut(final IServiceHandler<Result, Error> handler){
        auth.signOut(handler);
        CurrentUser.reset();
    }

    public void getProfile(final IServiceHandler<UserProfile, Error> handler){
        persistence.retrieve(handler);
    }

    public void changeAvatar(String path, final IServiceHandler<UserProfile, Error> handler ){
        persistence.changeAvatar(new File(path), handler);
    }


    public void updateProfile(EditProfile profile,final IServiceHandler<UserProfile, Error> handler ){
        persistence.update(profile, handler);
    }


    public void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler){
        persistence.getFriends(handler);
    }


}
