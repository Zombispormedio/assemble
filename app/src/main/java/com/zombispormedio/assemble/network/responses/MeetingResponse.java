package com.zombispormedio.assemble.network.responses;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.network.Error;

/**
 * Created by Xavier Serrano on 28/09/2016.
 */

public class MeetingResponse extends AbstractResponse<Meeting> {

    public MeetingResponse(boolean success, Error error,
            Meeting result) {
        super(success, error, result);
    }
}
