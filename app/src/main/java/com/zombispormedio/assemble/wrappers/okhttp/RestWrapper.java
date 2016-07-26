package com.zombispormedio.assemble.wrappers.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by Master on 25/07/2016.
 */
public class RestWrapper {
    private OkHttpClient client;

    private Request.Builder builder;

    public RestWrapper() {
        client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();


        builder=new Request.Builder();
    }

    public RestWrapper url(String url){
        builder.url(url);
        return this;
    }

    public RestWrapper header(String key, String value){
        builder.addHeader(key, value);
        return this;
    }


    public String get() throws IOException, JSONException {
        Request req=builder.get()
                .build();

        okhttp3.Response res= client.newCall(req).execute();



        return res.body().string();
    }

    public String post(String obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req=builder.post(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();



        return res.body().string();
    }

    public String put(String obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req=builder.put(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return res.body().string();
    }

    public String patch(String obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req=builder.patch(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return res.body().string();
    }

    public String delete() throws IOException, JSONException {
        Request req=builder.delete()
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return res.body().string();
    }



}
