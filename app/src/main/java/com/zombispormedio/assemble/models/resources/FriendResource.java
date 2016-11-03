package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.services.interfaces.IFriendService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.Result;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendResource extends AbstractResource<FriendProfile> {

    private final IFriendService apiService;

    private FriendSubscription friendSubscription;

    @Inject
    public FriendResource(IFriendService apiService,
            IStorageService<FriendProfile> storage) {
        super(storage);
        this.apiService = apiService;
    }

    public void searchNewFriends(String param, final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        apiService.searchNewFriends(param, handler);
    }

    public void requestNewFriend(int id, final IServiceHandler<Result, Error> handler) {
        apiService.requestNewFriend(id, handler);
    }

    public void deleteFriend(int id, final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        apiService.deleteFriend(id, new ServiceHandler<ArrayList<FriendProfile>, Error>(handler) {
            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                storage.createOrUpdateOrDeleteAll(result);
                haveChanged();
                super.onSuccess(result);
            }
        });
    }

    public int countAll() {
        return storage.countAll();
    }

    public ArrayList<FriendProfile> notIn(int[] ids) {
        return storage.notInByID(ids);
    }

    public void setFriendSubscription(FriendSubscription friendSubscription) {
        this.friendSubscription = friendSubscription;
    }

    private void haveChanged() {
        if (friendSubscription != null) {
            friendSubscription.haveChanged();
        }
    }

    public ArrayList<FriendProfile> getFriendInArrayofIds(int[] ids) {
        return storage.inByID(ids);
    }

}
