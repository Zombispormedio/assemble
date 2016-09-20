package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsResponse extends  ArrayResponse<Meeting>{

    public MeetingsResponse(boolean success, Error error,
            Meeting[] result) {
        super(success, error, result);
    }

}
