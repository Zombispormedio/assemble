package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.Chat;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public interface IChatsView extends IFragmentView{

    void bindChats(ArrayList<Chat> data);


}
