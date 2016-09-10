package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public interface IFriendsView extends IBaseView {

    void goToNewFriend();

    void bindFriends(ArrayList<FriendProfile> data);

    void bindFriendRequests(ArrayList<FriendRequestProfile> data);

    void loading();

    void unloading();

    void showFriendsList();

    void hideFriendsList();

    void showRequestsList();

    void hideRequestsList();

    void showEndOfLists();

    void hideEndOfLists();

}
