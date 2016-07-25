package com.zombispormedio.assemble.services;

import java.util.TreeMap;

/**
 * Created by Master on 10/07/2016.
 */
public interface IPersistenceService {
    //TODO

    void save(String userID, String table, Object value);
    void save(String userID, String table, TreeMap<String, String> values);

}
