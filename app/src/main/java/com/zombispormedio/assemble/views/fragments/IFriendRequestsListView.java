package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.models.FriendRequestProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public interface IFriendRequestsListView extends IFragmentView {

    void bindFriendRequests(ArrayList<FriendRequestProfile> data);

    void finishRefresh();

    void showFriendAccepted();

    void showFriendRejected();

}
