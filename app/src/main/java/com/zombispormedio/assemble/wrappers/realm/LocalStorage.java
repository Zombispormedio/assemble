package com.zombispormedio.assemble.wrappers.realm;


import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.dao.IDAOFactory;
import com.zombispormedio.assemble.models.BaseModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class LocalStorage<D extends RealmObject, M extends BaseModel> {

    private Class<? extends D> opClass;

    protected IDAOFactory<D> factory;

    private Realm database;

    public LocalStorage(Class<D> opClass, IDAOFactory<D> factory) {
        this.opClass = opClass;
        this.factory=factory;
        database = Configuration.getInstance().getDatabase();
    }

    public void create(M params) {
        D object = factory.create();

        database.beginTransaction();

        ((IBaseDAO<M>) object).fromModel(params);
        database.copyToRealm(object);

        database.commitTransaction();
    }

    public void update(D object, M params) {
        database.beginTransaction();
        ((IBaseDAO<M>) object).fromModel(params);
        database.copyToRealmOrUpdate(object);
        database.commitTransaction();
    }

    public void updateAll(ArrayList<M> params){
        database.beginTransaction();
        ArrayList<D> objects=new ArrayList<>();
        for(int i=0; i<params.size();i++){
            D object= factory.create();
            ((IBaseDAO<M>) object).fromModel(params.get(i));
            objects.add(object);
        }
        database.copyToRealmOrUpdate(objects);
        database.commitTransaction();
    }

    public D getById(int id) {
        RealmQuery<D> query = getQuery();

        query.equalTo("id", id);

        return query.findFirst();
    }

    public D getFirst() {
        RealmQuery<D> query = getQuery();

        return query.findFirst();
    }

    public ArrayList<D> getAll() {
        RealmQuery<D> query = getQuery();

        RealmResults<D> realmResults = query.findAll();

        ArrayList<D> results = new ArrayList<>();

        for (int i = 0; i < realmResults.size(); i++) {
            results.add(realmResults.get(i));
        }
        return results;
    }

    public void deleteByID(int id){
        RealmQuery<D> query = getQuery();

        query.equalTo("id", id);

        D object =query.findFirst();


        if(object!=null){
            database.beginTransaction();
            object.deleteFromRealm();
            database.commitTransaction();
        }
    }

    public void delete(D object){
        if(object!=null){
            database.beginTransaction();
            object.deleteFromRealm();
            database.commitTransaction();
        }
    }

    private RealmQuery<D> getQuery() {
        return (RealmQuery<D>) database.where(opClass);
    }

    public static class Configuration {

        private static Configuration ourInstance = new Configuration();

        private Realm database;

        public static Configuration getInstance() {
            return ourInstance;
        }

        public Realm getDatabase() {
            return database;
        }

        public void setDatabase(Realm database) {
            this.database = database;
        }
    }
}
