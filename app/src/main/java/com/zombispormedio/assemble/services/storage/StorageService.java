package com.zombispormedio.assemble.services.storage;


import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.models.BaseModel;
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
    public void createOrUpdateAll(ArrayList<M> params) {
        ArrayList<D> objects=new ArrayList<>();
        for(int i=0; i<params.size();i++){
            D object= factory.create();
            ((IBaseDAO<M>) object).fromModel(params.get(i));
            objects.add(object);
        }

        storage.updateAll(objects);
    }

    @Override
    public void createOrUpdateOrDeleteAll(ArrayList<M> params) {
        ArrayList<D> objects= storage.getAll();
        ArrayList<Integer> updIds=new ArrayList<>();

        createOrUpdateAll(params);

        for(M param : params){
            updIds.add(param.id);
        }

        for(D object : objects){
            int id= ((IBaseDAO<M>)object).getId();

            if(!updIds.contains(id)){
                storage.delete(object);
            }
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
