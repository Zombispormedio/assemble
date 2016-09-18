package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.services.storage.IStorageService;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendRequestResource extends ConceptResource<FriendRequestProfile> {

    @Inject
    public FriendRequestResource(
            IStorageService<FriendRequestProfile> storage) {
        super(storage);
    }
}
