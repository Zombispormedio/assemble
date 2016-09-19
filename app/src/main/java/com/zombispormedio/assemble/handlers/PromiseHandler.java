package com.zombispormedio.assemble.handlers;

import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.JsonBinder;
import com.zombispormedio.assemble.net.responses.AbstractResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class PromiseHandler<T extends AbstractResponse, R>  implements IPromiseHandler{

    private IServiceHandler<R, Error> handler;

    public PromiseHandler(IServiceHandler<R, Error> handler) {
        this.handler = handler;
    }


    @Override
    public void onSuccess(String... args) {
        try {
            T res = getResponse(args[0]);

            if (res.success) {
                R result=getResult(res);
                handler.onSuccess(result);
            } else {
                handler.onError(res.error);
            }
        } catch (IOException e) {
            handler.onError(new Error(e.getMessage()));
        }
    }

    protected T getResponse(String arg) throws IOException {
        return (T) JsonBinder.toDefaultResponse(arg);
    }

    protected R getResult(T res){
        return (R) res.result;
    }

    @Override
    public void onNotConnected() {
        handler.onNotConnected();
    }
}
