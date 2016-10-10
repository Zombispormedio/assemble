package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.models.Message;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public interface IChatView extends IBaseView {

    void bindTitle(String title);

    void setAvatar(String path, String letter);

    void bindMessages(ArrayList<Message> messages);

    String getMessageInputValue();

    int addPendingMessage(Message message);

    void addMessage(int index, Message message);

}
