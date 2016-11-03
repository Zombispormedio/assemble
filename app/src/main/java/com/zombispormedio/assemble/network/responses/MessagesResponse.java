package com.zombispormedio.assemble.network.responses;

import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.network.Error;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessagesResponse extends ArrayResponse<Message> {

    public MessagesResponse(boolean success, Error error,
            Message[] result) {
        super(success, error, result);
    }
}
