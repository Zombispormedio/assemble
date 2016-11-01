package com.zombispormedio.assemble.wrappers.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class JSONWrapper<T> {

    private final JsonAdapter<T> adapter;

    public JSONWrapper(Class<T> klass) {
        Moshi moshi = new Moshi.Builder().build();
        adapter = moshi.adapter(klass);

    }

    public T fromJSON(@NonNull String json) throws IOException {
        return adapter.fromJson(json);
    }

    @NonNull
    public String toJSON(T obj) {
        return adapter.toJson(obj);
    }
}
