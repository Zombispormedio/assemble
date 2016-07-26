package com.zombispormedio.assemble.wrappers.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

/**
 * Created by Master on 26/07/2016.
 */
public class JSONWrapper<T> {
   private JsonAdapter<T> adapter;
    private Moshi moshi;

    public JSONWrapper(Class<T> klass) {
        moshi= new Moshi.Builder().build();
        adapter=moshi.adapter(klass);

    }

    public T fromJSON(String json) throws IOException {
        return adapter.fromJson(json);
    }

    public String toJSON(T obj){
        return adapter.toJson(obj);
    }
}
