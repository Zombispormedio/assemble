package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.Error;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileService {

    void retrieve(final IServiceHandler<UserProfile, Error> handler);

    void changeAvatar(File file, final IServiceHandler<UserProfile, Error> handler);

    void changeAvatar(String url, final IServiceHandler<UserProfile, Error> handler);

    void update(EditProfile profile, final IServiceHandler<UserProfile, Error> handler);

    void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler);

    void getFriendRequests(final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler);

}
