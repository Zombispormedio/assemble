package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 19/09/2016.
 */
public interface IFriendService {
    void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler);

    void getFriendRequests(final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler);

    void searchNewFriends(String paramSearch, final IServiceHandler<ArrayList<FriendProfile>, Error> handler);

    void requestNewFriend(int friendId, final IServiceHandler<Result, Error> handler);

    void acceptRequest(int friendId, final  IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler);

    void rejectRequest(int friendId, final  IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler);

    void deleteFriend(int friendId,  final IServiceHandler<ArrayList<FriendProfile>, Error> handler);

}
