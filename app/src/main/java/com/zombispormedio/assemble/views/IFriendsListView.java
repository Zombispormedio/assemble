package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public interface IFriendsListView extends IFragmentView{

    void bindFriends(ArrayList<FriendProfile> data);

    void finishRefresh();

    void showRemovedFriend();
}
