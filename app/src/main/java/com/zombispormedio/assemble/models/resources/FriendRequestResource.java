package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.services.interfaces.IFriendService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestResource extends ConceptResource<FriendRequestProfile> {

    private IFriendService apiService;

    @Inject
    public FriendRequestResource(IFriendService apiService,
            IStorageService<FriendRequestProfile> storage) {
        super(storage);
        this.apiService = apiService;
    }



}
