package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsResponse extends ArrayResponse<Chat> {

    public ChatsResponse(boolean success, Error error,
            Chat[] result) {
        super(success, error, result);
    }

}
