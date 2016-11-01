package com.zombispormedio.assemble.net;


import com.annimon.stream.Stream;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.wrappers.okhttp.RestWrapper;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class AsyncRequest extends AsyncTask<Request, Void, Promise> {

    @NonNull
    @Override
    protected Promise doInBackground(Request... requests) {
        Request req = requests[0];
        String result = "";
        RestWrapper rest = new RestWrapper()
                .url(req.getUrl());

        HashMap<String, String> headers = req.getHeaders();
        if (headers != null) {
            Stream.of(headers)
                    .forEach((entry) -> rest.header(entry.getKey(), entry.getValue()));
        }

        try {

            switch (req.getMethod()) {
                case GET:
                    result = rest.get();
                    break;

                case POST: {
                    String body = req.getBody();
                    FileBody file = req.getFile();
                    result = body != null ?
                            rest.post(body) :
                            file != null ?
                                    rest.post(file) :
                                    rest.post();

                    break;
                }

                case PUT: {
                    String body = req.getBody();
                    FileBody file = req.getFile();
                    result = body != null ?
                            rest.put(body) :
                            file != null ?
                                    rest.put(file) :
                                    rest.put();
                    break;
                }

                case PATCH: {
                    String body = req.getBody();
                    FileBody file = req.getFile();
                    result = body != null ?
                            rest.patch(body) :
                            file != null ?
                                    rest.patch(file) :
                                    rest.patch();
                    break;
                }

                case DELETE:
                    result = rest.delete();
                    break;
            }

        } catch (IOException e) {
            Logger.d(e);
        }

        return new Promise(result, req.getHandler());
    }

    protected void onPostExecute(@NonNull Promise promise) {
        super.onPostExecute(promise);
        promise.handle();
    }


}
