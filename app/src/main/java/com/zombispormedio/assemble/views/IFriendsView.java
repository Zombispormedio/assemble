package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public interface IFriendsView extends IBaseView {

   void goToNewFriend();

    void bindFriends(ArrayList<FriendProfile> data);
    void bindRequestFriends(ArrayList<FriendProfile> data);

    void loading();

    void unloading();

}
