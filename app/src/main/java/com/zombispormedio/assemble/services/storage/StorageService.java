package com.zombispormedio.assemble.services.storage;


import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class StorageService<D extends RealmObject, M extends BaseModel> implements IStorageService<M> {


    private LocalStorage<D> storage;

    private IDAOFactory<D> factory;


    public StorageService(LocalStorage<D> storage, IDAOFactory<D> factory) {
        this.storage = storage;
        this.factory= factory;
    }

    @Override
    public void create(M params) {

    }

    @Override
    public void update(M params) {

    }

    @Override
    public void createOrUpdate(M params) {

    }

    @Override
    public M getFirst() {
        return null;
    }

    @Override
    public ArrayList<M> getAll() {
        return null;
    }

    @Override
    public M getByID(int id) {
        return null;
    }
}
