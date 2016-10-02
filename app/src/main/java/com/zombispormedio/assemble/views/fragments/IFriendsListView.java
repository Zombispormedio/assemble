package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.fragments.IFragmentView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public interface IFriendsListView extends IFragmentView {

    void bindFriends(ArrayList<FriendProfile> data);

    void finishRefresh();

    void showRemovedFriend();
}
