package com.zombispormedio.assemble;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.rest.AsyncRequest;
import com.zombispormedio.assemble.rest.METHOD;
import com.zombispormedio.assemble.rest.Request;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
/**
 * Created by Master on 26/07/2016.
 */
public class AsyncRequestUnitTest {

    @Test
    public void get_isCorrect() throws Exception {
        Request req=new Request.Builder()
                .url("https://assemble-api.herokuapp.com")
                .get()
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        System.out.print(args[0]);

                    }
                })
                .build();
        Logger.d(req);

        new AsyncRequest().execute(req);







    }
}
