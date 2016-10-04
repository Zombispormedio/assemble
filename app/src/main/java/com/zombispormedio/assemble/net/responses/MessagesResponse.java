package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.net.Error;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessagesResponse extends ArrayResponse<Message> {

    public MessagesResponse(boolean success, Error error,
            Message[] result) {
        super(success, error, result);
    }
}
