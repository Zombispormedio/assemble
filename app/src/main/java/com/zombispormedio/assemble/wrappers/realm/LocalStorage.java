package com.zombispormedio.assemble.wrappers.realm;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class LocalStorage<T extends RealmObject> {

    private Class<? extends T> opClass;

    private Realm database;

    public LocalStorage(Class<T> opClass) {
        this.opClass = opClass;
        database = Configuration.getInstance().getDatabase();
    }

    public void create(T object) {
        database.beginTransaction();
        database.copyToRealm(object);
        database.commitTransaction();
    }

    public void update(T object) {
        database.beginTransaction();
        database.copyToRealmOrUpdate(object);
        database.commitTransaction();
    }

    public void updateAll(ArrayList<T> objects){
        database.beginTransaction();
        database.copyToRealmOrUpdate(objects);
        database.commitTransaction();
    }

    public T getById(int id) {
        RealmQuery<T> query = getQuery();

        query.equalTo("id", id);

        return query.findFirst();
    }

    public T getFirst() {
        RealmQuery<T> query = getQuery();

        return query.findFirst();
    }

    public ArrayList<T> getAll() {
        RealmQuery<T> query = getQuery();

        RealmResults<T> realmResults = query.findAll();

        ArrayList<T> results = new ArrayList<>();

        for (int i = 0; i < realmResults.size(); i++) {
            results.add(realmResults.get(i));
        }
        return results;
    }

    public void deleteByID(int id){
        RealmQuery<T> query = getQuery();

        query.equalTo("id", id);

        T object =query.findFirst();


        if(object!=null){
            database.beginTransaction();
            object.deleteFromRealm();
            database.commitTransaction();
        }
    }

    public void delete(T object){
        if(object!=null){
            database.beginTransaction();
            object.deleteFromRealm();
            database.commitTransaction();
        }
    }

    private RealmQuery<T> getQuery() {
        return (RealmQuery<T>) database.where(opClass);
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
