package com.zombispormedio.assemble.views;


import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public interface INewFriendView extends IBaseView {

    void bindSearchResults(ArrayList<FriendProfile> results);

    String getSearchText();

    void showProgress();

    void hideProgress();


}
