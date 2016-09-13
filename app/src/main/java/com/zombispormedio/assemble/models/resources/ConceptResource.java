package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ConceptResource<T> {

    protected IStorageService<T> storage;

    public ConceptResource(IStorageService<T> storage) {
        this.storage = storage;
    }

    public ArrayList<T> getAll(){
        return storage.getAll();
    }
}
