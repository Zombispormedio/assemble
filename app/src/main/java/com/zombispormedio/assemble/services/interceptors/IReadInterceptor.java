package com.zombispormedio.assemble.services.interceptors;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface IReadInterceptor extends InterceptorInterface {

    void notifyHomeForChat(int chatId, boolean read);

    void readMessages(int[] messageIds);

}
