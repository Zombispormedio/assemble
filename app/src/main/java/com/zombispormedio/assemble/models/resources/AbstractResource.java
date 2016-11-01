package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.models.services.storage.IStorageService;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public abstract class AbstractResource<T> {

    protected final IStorageService<T> storage;

    public AbstractResource(IStorageService<T> storage) {
        this.storage = storage;
    }

    public ArrayList<T> getAll() {
        return storage.getAll();
    }

    @Nullable
    public T getFirst() {
        return storage.getFirst();
    }


}
