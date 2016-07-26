package com.zombispormedio.assemble.rest;

import android.app.backup.RestoreObserver;
import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.wrappers.okhttp.RestWrapper;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        HashMap<String, String> headers=req.getHeaders();
        if (headers != null){
            for (Map.Entry<String,String> entry : headers.entrySet()) {
                String value = entry.getValue();
                String key = entry.getKey();

                rest.header(key, value);
            }
        }




        try {

            switch (req.getMethod()){
            case GET:result=rest.get();
                break;

            case POST: result= rest.post(req.getBody());
                break;

            case PUT: result= rest.put(req.getBody());
                break;

            case PATCH: result=rest.patch(req.getBody());
                break;

            case DELETE: result=rest.delete();
                break;
        }

        } catch (IOException e) {
            Logger.d(e);
        } catch (JSONException e) {
            Logger.d(e);
        }


        return new Promise(result, req.getHandler());
    }

    protected void onPostExecute(Promise promise) {
        super.onPostExecute(promise);
        promise.handle();
    }



}
