package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.models.UserProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public interface IStorageService<T> {

    void create(T params);

    void update(T params);

    void createOrUpdate(T params);

    T getFirst();

    ArrayList<T> getAll();

    T getByID(int id);

}
