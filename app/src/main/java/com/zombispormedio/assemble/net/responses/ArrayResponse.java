package com.zombispormedio.assemble.net.responses;

import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 20/09/2016.
 */

public class ArrayResponse<M extends BaseModel> extends AbstractResponse<M[]>{

    public ArrayResponse(boolean success, Error error, M[] result) {
        super(success, error, result);
    }

    public ArrayList<M> getResult() {
        return new ArrayList<>(Arrays.asList(result));
    }
}
