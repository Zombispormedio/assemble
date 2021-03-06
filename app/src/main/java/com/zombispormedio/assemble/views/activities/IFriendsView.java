package com.zombispormedio.assemble.views.activities;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public interface IFriendsView extends IBaseView {

    void goToNewFriend();

    void loading();

    void unloading();

    void showLists();

    void hideLists();

    void goToFriendsList();

    void goToFriendRequestsList();

    void onFriendsTab();

    void onFriendRequestsTab();

}
