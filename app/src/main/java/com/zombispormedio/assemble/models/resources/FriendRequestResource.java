package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.responses.ArrayResponse;
import com.zombispormedio.assemble.services.interfaces.IFriendService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestResource extends ConceptResource<FriendRequestProfile> {

    private IFriendService apiService;

    private FriendSubscription friendSubscription;

    private FriendRequestSubscription friendRequestSubscription;

    @Inject
    public FriendRequestResource(IFriendService apiService,
            IStorageService<FriendRequestProfile> storage) {
        super(storage);
        this.apiService = apiService;
    }


    public void rejectRequest(int id, final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler){

    }

    public void acceptRequest(int id, final IServiceHandler<ArrayList<FriendProfile>, Error> handler){

    }

    public void setFriendSubscription(FriendSubscription friendSubscription) {
        this.friendSubscription = friendSubscription;
    }

    public void setFriendRequestSubscription(
            FriendRequestSubscription friendRequestSubscription) {
        this.friendRequestSubscription = friendRequestSubscription;
    }

    private void haveFriendRequestsChanged(){
        if (friendRequestSubscription!=null){
            friendRequestSubscription.haveChanged();
        }
    }

    private void haveFriendsChanged(){
        if (friendSubscription!=null){
            friendSubscription.haveChanged();
        }
    }
}
