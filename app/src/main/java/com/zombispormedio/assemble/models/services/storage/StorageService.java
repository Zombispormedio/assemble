package com.zombispormedio.assemble.models.services.storage;


import com.zombispormedio.assemble.wrappers.realm.dao.IBaseDAO;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class StorageService<D extends RealmObject, M extends BaseModel> implements IStorageService<M> {


    protected LocalStorage<D, M> storage;


    public StorageService(LocalStorage<D, M> storage) {
        this.storage = storage;
    }

    @Override
    public void create(M params) {

        storage.create(params);
    }

    @Override
    public void update(M params) {
        D object = storage.getById(params.id);

        if (object != null) {
            storage.update(object, params);
        }
    }

    @Override
    public void createOrUpdate(M params) {
        D object = storage.getById(params.id);
        if (object != null) {
            storage.update(object, params);
        } else {
            create(params);
        }
    }

    @Override
    public void createOrUpdateAll(ArrayList<M> params) {

        storage.updateAll(params);
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
        ArrayList<D> objects= storage.getAll();
        return toModel(objects);
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

    @Override
    public int countAll() {
        return storage.countAll();
    }

    public ArrayList<M> inByID(int[] in){
        return toModel(storage.in("id", Utils.toInteger(in)));
    }

    @Override
    public ArrayList<M> notInByID(int[] in) {
        return toModel(storage.notIn("id", Utils.toInteger(in)));
    }


    private ArrayList<M> toModel(ArrayList<D> objects){
        ArrayList<M> result= new ArrayList<>();
        for (D object: objects) {
            result.add(((IBaseDAO<M>) object).toModel());
        }
        return result;
    }

    public LocalStorage<D, M> getStorage() {
        return storage;
    }
}