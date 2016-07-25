package com.zombispormedio.assemble.adapters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by Master on 25/07/2016.
 */
public class RestifyAdapter {
    private OkHttpClient client;

    private Request.Builder builder;

    public RestifyAdapter() {
        client = new OkHttpClient();
        builder=new Request.Builder();
    }

    public RestifyAdapter url(String url){
        builder.url(url);
        return this;
    }

    public RestifyAdapter header(String key, String value){
        builder.addHeader(key, value);
        return this;
    }

    public class Response {
        private JSONObject body;

        public Response(JSONObject body) {
            this.body = body;
        }

        public JSONObject getBody() {
            return body;
        }
    }

    public Response get() throws IOException, JSONException {
        Request req=builder.get()
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return new Response(body);
    }

    public Response post(JSONObject obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj.toString());

        Request req=builder.post(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return new Response(body);
    }

    public Response put(JSONObject obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj.toString());

        Request req=builder.put(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return new Response(body);
    }

    public Response patch(JSONObject obj) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj.toString());

        Request req=builder.patch(reqBody)
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return new Response(body);
    }

    public Response delete() throws IOException, JSONException {
        Request req=builder.delete()
                .build();

        okhttp3.Response res= client.newCall(req).execute();

        JSONObject body= new JSONObject(res.body().string());

        return new Response(body);
    }



}
