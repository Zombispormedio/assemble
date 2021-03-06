package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.services.interfaces.IFriendService;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 19/09/2016.
 */
public class FriendAPIService implements IFriendService {

    @NonNull
    private final APIConfiguration api;

    public FriendAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/friends")
                .handler(DeferUtils.deferFriends(handler))
                .get();
    }

    @Override
    public void getFriendRequests(final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {
        api.RestWithAuth("/friend_requests")
                .handler(DeferUtils.deferFriendRequests(handler))
                .get();
    }

    @Override
    public void searchNewFriends(String paramSearch, final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/new_friends/:search_text")
                .params("search_text", paramSearch)
                .handler(DeferUtils.deferFriends(handler))
                .get();
    }

    @Override
    public void requestNewFriend(int friendId, IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/new_friend/:id")
                .params("id", friendId)
                .handler(DeferUtils.defer(handler))
                .post();
    }

    @Override
    public void acceptRequest(int friendId, IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {
        api.RestWithAuth("/accept_friend/:id")
                .params("id", friendId)
                .handler(DeferUtils.deferFriendRequests(handler))
                .post();
    }

    @Override
    public void rejectRequest(int friendId, IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {
        api.RestWithAuth("/reject_friend/:id")
                .params("id", friendId)
                .handler(DeferUtils.deferFriendRequests(handler))
                .post();
    }

    @Override
    public void deleteFriend(int friendId, IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/delete_friend//:id")
                .params("id", friendId)
                .handler(DeferUtils.deferFriends(handler))
                .delete();
    }


}
