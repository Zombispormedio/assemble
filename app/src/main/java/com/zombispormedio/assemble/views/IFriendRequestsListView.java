package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendRequestProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public interface IFriendRequestsListView {

    void bindFriendRequests(ArrayList<FriendRequestProfile> data);

}
