package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public interface ICreateChatView extends IBaseView{

    void bindFriends(ArrayList<FriendProfile> friends);

    void showProgress();

    void hideProgress();

    void goHome();

}
