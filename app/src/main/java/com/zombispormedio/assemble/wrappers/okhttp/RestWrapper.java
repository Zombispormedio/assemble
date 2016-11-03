package com.zombispormedio.assemble.wrappers.okhttp;

import com.zombispormedio.assemble.network.FileBody;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by Master on 25/07/2016.
 */
public class RestWrapper {

    @NonNull
    private final OkHttpClient client;

    @NonNull
    private final Request.Builder builder;

    public RestWrapper() {
        client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        builder = new Request.Builder();
    }

    @NonNull
    public RestWrapper url(@NonNull String url) {
        builder.url(url);
        return this;
    }

    @NonNull
    public RestWrapper header(@NonNull String key, @NonNull String value) {
        builder.addHeader(key, value);
        return this;
    }


    @NonNull
    public String get() throws IOException {
        Request req = builder.get()
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String post(@NonNull String obj) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req = builder.post(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }


    @NonNull
    public String post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, "{}");

        Request req = builder.post(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String post(@NonNull FileBody file) throws IOException {
        MediaType mediaType = MediaType.parse(file.getMediaType());
        RequestBody reqBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(file.getKey(), file.getFilename(),
                        RequestBody.create(mediaType, file.getFile()))
                .build();

        Request req = builder.post(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String put(@NonNull String obj) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req = builder.put(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String put() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, "{}");

        Request req = builder.put(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String put(@NonNull FileBody file) throws IOException {
        MediaType mediaType = MediaType.parse(file.getMediaType());
        RequestBody reqBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(file.getKey(), file.getFilename(),
                        RequestBody.create(mediaType, file.getFile()))
                .build();

        Request req = builder.put(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String patch() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, "{}");

        Request req = builder.put(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String patch(@NonNull String obj) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody reqBody = RequestBody.create(mediaType, obj);

        Request req = builder.patch(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String patch(@NonNull FileBody file) throws IOException {
        MediaType mediaType = MediaType.parse(file.getMediaType());
        RequestBody reqBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(file.getKey(), file.getFilename(),
                        RequestBody.create(mediaType, file.getFile()))
                .build();

        Request req = builder.patch(reqBody)
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }

    @NonNull
    public String delete() throws IOException {
        Request req = builder.delete()
                .build();

        okhttp3.Response res = client.newCall(req).execute();

        return res.body().string();
    }


}
