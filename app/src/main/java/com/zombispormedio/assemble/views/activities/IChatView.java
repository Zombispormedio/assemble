package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IBaseView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public interface IChatView extends IBaseView {

    void bindTitle(String title);

    void setAvatar(String path, String letter);

    void bindMessages(ArrayList<Message> messages);

    String getMessageInputValue();

    Utils.IntPair addPendingMessage(Message message);

    void addMessage(Utils.IntPair tuple, Message message);

}
