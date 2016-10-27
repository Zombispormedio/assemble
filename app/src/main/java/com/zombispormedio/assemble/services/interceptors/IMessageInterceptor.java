package com.zombispormedio.assemble.services.interceptors;

import com.zombispormedio.assemble.models.Message;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface IMessageInterceptor extends InterceptorInterface {

    void saveMessage(Message message);

    boolean isInHome();

    boolean isInTheSameChat(int chatId);

    void notifyHomeForChat(int chatId);

    void notifyChat(int messageId);

}
