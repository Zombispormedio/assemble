package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.adapters.AbstractHolder;
import com.zombispormedio.assemble.models.FriendProfile;

import java.util.List;

/**
 * Created by Xavier Serrano on 17/10/2016.
 */

public class FriendProfileAdapter<H extends AbstractHolder<FriendProfile>> extends BaseListAdapter<FriendProfile, H> {

    public FriendProfileAdapter(List<FriendProfile> data) {
        super(FriendProfile.class);
        addAll(data);
    }

    public FriendProfileAdapter() {
        super(FriendProfile.class);
    }
}
