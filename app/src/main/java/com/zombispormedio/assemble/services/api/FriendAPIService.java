package com.zombispormedio.assemble.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.FriendRequestsResponse;
import com.zombispormedio.assemble.net.responses.FriendsResponse;
import com.zombispormedio.assemble.services.interfaces.IFriendService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 19/09/2016.
 */
public class FriendAPIService implements IFriendService {

    private APIConfiguration api;

    public FriendAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/friends")
                .handler(deferFriends(handler))
                .get();
    }

    @Override
    public void getFriendRequests(final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {
        api.RestWithAuth("/friend_requests")
                .handler(deferFriendRequests(handler))
                .get();
    }

    @Override
    public void searchNewFriends(String paramSearch, final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/new_friends/:search_text")
                .params("search_text", paramSearch)
                .handler(deferFriends(handler))
                .get();
    }


    private PromiseHandler deferFriends(IServiceHandler<ArrayList<FriendProfile>, Error> handler){
        return new PromiseHandler<FriendsResponse,ArrayList<FriendProfile>>(handler) {
            @Override
            protected FriendsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toFriendsResponse(arg);
            }

            @Override
            protected ArrayList<FriendProfile> getResult(FriendsResponse res) {
                return res.getResult();
            }
        };
    }

    private PromiseHandler deferFriendRequests(IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler){

        return new PromiseHandler<FriendRequestsResponse, ArrayList<FriendRequestProfile>>(handler) {
            @Override
            protected FriendRequestsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toFriendRequestsResponse(arg);
            }

            @Override
            protected ArrayList<FriendRequestProfile> getResult(FriendRequestsResponse res) {
                return res.getResult();
            }
        };
    }
}
