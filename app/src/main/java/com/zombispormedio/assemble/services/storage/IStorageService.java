package com.zombispormedio.assemble.services.storage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public interface IStorageService<T> {

    void create(T params);

    void update(T params);

    void createOrUpdate(T params);

    void createOrUpdateAll(ArrayList<T> params);

    T getFirst();

    ArrayList<T> getAll();

    T getByID(int id);

}
