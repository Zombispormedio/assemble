package com.zombispormedio.assemble.models.services.storage;


import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.wrappers.realm.dao.IBaseDAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class StorageService<D extends RealmObject, M extends BaseModel> implements IStorageService<M> {


    protected final LocalStorage<D, M> storage;


    public StorageService(LocalStorage<D, M> storage) {
        this.storage = storage;
    }

    @Override
    public void create(M params) {
        storage.create(params);
    }

    @Override
    public void update(@NonNull M params) {
        D object = storage.getById(params.id);

        if (object != null) {
            storage.update(object, params);
        }
    }

    @Override
    public void createOrUpdate(@NonNull M params) {
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
    public void createOrUpdateOrDeleteAll(@NonNull ArrayList<M> params) {
        if (params.size() > 0) {
            Integer[] ids = Stream.of(params)
                    .map(item -> item.id)
                    .toArray(Integer[]::new);

            createOrUpdateAll(params);

            ArrayList<D> objects = storage.notIn("id", ids);

            if (objects.size() > 0) {
                Stream.of(objects)
                        .forEach(storage::delete);
            }

        }

    }


    @Override
    public M getFirst() {
        M result = null;
        D object = storage.getFirst();
        if (object != null) {
            result = ((IBaseDAO<M>) object).toModel();
        }
        return result;
    }

    @Override
    public ArrayList<M> getAll() {
        ArrayList<D> objects = storage.getAll();
        return toModel(objects);
    }


    @Override
    public M getByID(int id) {
        M result = null;
        D object = storage.getById(id);

        if (object != null) {
            result = ((IBaseDAO<M>) object).toModel();
        }

        return result;
    }

    @Override
    public int countAll() {
        return storage.countAll();
    }

    public ArrayList<M> inByID(int[] in) {
        return toModel(storage.in("id", Utils.toInteger(in)));
    }

    @Override
    public ArrayList<M> notInByID(int[] in) {
        return toModel(storage.notIn("id", Utils.toInteger(in)));
    }

    @Override
    public void close() {
        storage.close();
    }


    private ArrayList<M> toModel(@NonNull ArrayList<D> objects) {
        return Stream.of(objects)
                .map(object -> ((IBaseDAO<M>) object).toModel())
                .collect(Collectors.toCollection(ArrayList<M>::new));
    }

    public LocalStorage<D, M> getStorage() {
        return storage;
    }
}
