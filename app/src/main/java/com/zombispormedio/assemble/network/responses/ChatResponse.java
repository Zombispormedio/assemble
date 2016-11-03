package com.zombispormedio.assemble.network.responses;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.network.Error;

/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class ChatResponse extends AbstractResponse<Chat> {

    public ChatResponse(boolean success, Error error,
            Chat result) {
        super(success, error, result);
    }
}
