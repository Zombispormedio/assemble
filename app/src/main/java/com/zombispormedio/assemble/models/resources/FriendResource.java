package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.services.interfaces.IFriendService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendResource extends ConceptResource<FriendProfile> {

    private IFriendService apiService;
    @Inject
    public FriendResource(IFriendService apiService,
            IStorageService<FriendProfile> storage) {
        super(storage);
        this.apiService = apiService;
    }

    public void searchNewFriends(String param, final IServiceHandler<ArrayList<FriendProfile>, Error> handler){
        apiService.searchNewFriends(param, handler);
    }

    public void requestNewFriend(int id, final IServiceHandler<Result, Error> handler){
        apiService.requestNewFriend(id, handler);
    }
}
