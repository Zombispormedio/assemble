package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 24/08/2016.
 */
public interface IFriendsView extends IBaseView {

    void goToNewFriend();

    void loading();

    void unloading();

    void showLists();

    void hideLists();

}
