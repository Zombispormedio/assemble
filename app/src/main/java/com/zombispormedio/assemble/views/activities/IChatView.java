package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 02/10/2016.
 */

public interface IChatView extends IBaseView {

    void bindTitle(String title);

    void setAvatar(ImageUtils.ImageBuilder builder);

    void bindMessages(ArrayList<Message> messages);

    String getMessageInputValue();

    int addPendingMessage(Message message);

    void addMessage(int index, Message message);

}
