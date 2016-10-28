package com.zombispormedio.assemble.services.interceptors;

import com.zombispormedio.assemble.models.Message;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface IMessageInterceptor extends InterceptorInterface {

    void saveMessage(Message message);

}
