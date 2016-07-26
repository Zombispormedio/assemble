package com.zombispormedio.assemble.rest;

import android.app.backup.RestoreObserver;
import android.os.AsyncTask;

import com.zombispormedio.assemble.wrappers.okhttp.RestWrapper;

/**
 * Created by Master on 26/07/2016.
 */
public class AsyncRequest extends AsyncTask<Request, Void, Promise> {
    @Override
    protected Promise doInBackground(Request... requests) {
        Request req=requests[0];
        String result="";
        RestWrapper rest= new RestWrapper()
                .url(req.getUrl());




        switch(req.getMethod()){
            case GET:


                break;
        }


        return new Promise(result, req.getHandler());
    }

    protected void onPostExecute(Promise promise) {

    }



}
