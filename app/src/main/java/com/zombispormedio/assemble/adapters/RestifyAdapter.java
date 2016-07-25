package com.zombispormedio.assemble.adapters;

import okhttp3.OkHttpClient;
import okhttp3.Request;

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

    public RestifyAdapter()
}
