package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.models.Chat;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public interface IChatsView extends IFragmentView {

    void bindChats(ArrayList<Chat> data);

    void updateChat(Chat chat);

    void finishRefresh();

    void goToChat(int friendId);

}
