package com.zombispormedio.assemble.models.services.storage;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public interface IStorageService<T> {

    void create(T params);

    void update(T params);

    void createOrUpdate(T params);

    void createOrUpdateAll(ArrayList<T> params);

    void createOrUpdateOrDeleteAll(ArrayList<T> params);

    @Nullable
    T getFirst();

    ArrayList<T> getAll();

    @Nullable
    T getByID(int id);

    int countAll();

    ArrayList<T> inByID(int[] in);

    ArrayList<T> notInByID(int[] in);

    void close();

}
