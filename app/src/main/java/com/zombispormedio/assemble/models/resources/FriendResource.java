package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.services.storage.IStorageService;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class FriendResource extends ConceptResource<FriendProfile> {

    public FriendResource(
            IStorageService<FriendProfile> storage) {
        super(storage);
    }
}
