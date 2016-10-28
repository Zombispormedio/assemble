package com.zombispormedio.assemble.services.interceptors;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface IReadInterceptor extends InterceptorInterface {

    void readMessages(int[] messageIds);

    void notifyReadToChat(int[] messageIds);

}
