package com.zombispormedio.assemble.wrappers;

import android.util.Pair;

import java.util.TreeMap;

/**
 * Created by Master on 10/07/2016.
 */
public interface IPersistenceWrapper {
    //TODO

    void save(String userID, String table, Object value);
    void save(String userID, String table, TreeMap<String, String> values);

}
