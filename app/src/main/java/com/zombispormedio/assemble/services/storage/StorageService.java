package com.zombispormedio.assemble.services.storage;


import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class StorageService<D extends RealmObject, M extends BaseModel> implements IStorageService<M> {


    protected LocalStorage<D> storage;

    protected IDAOFactory<D> factory;


    public StorageService(LocalStorage<D> storage, IDAOFactory<D> factory) {
        this.storage = storage;
        this.factory = factory;
    }

    @Override
    public void create(M params) {
        D object = factory.create();

        ((IBaseDAO<M>) object).fromModel(params);

        storage.create(object);
    }

    @Override
    public void update(M params) {
        D object = storage.getById(params.id);

        if (object != null) {
            ((IBaseDAO<M>) object).fromModel(params);
            storage.update(object);
        }
    }

    @Override
    public void createOrUpdate(M params) {
        D object = storage.getById(params.id);

        if (object != null) {
            ((IBaseDAO<M>) object).fromModel(params);
            storage.update(object);
        } else {
            create(params);
        }
    }

    @Override
    public M getFirst() {
        M result=null;
        D object=storage.getFirst();
        if(object!=null){
            result= ((IBaseDAO<M>) object).toModel();
        }
        return result;
    }

    @Override
    public ArrayList<M> getAll() {
        ArrayList<M> result= new ArrayList<>();

        ArrayList<D> objects= storage.getAll();

        for (D object: objects) {
            result.add(((IBaseDAO<M>) object).toModel());
        }

        return result;
    }

    @Override
    public M getByID(int id) {
        M result=null;
        D object=storage.getById(id);

        if(object!=null){
            result=((IBaseDAO<M>) object).toModel();
        }

        return result;
    }
}
