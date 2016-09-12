package com.zombispormedio.assemble.wrappers.realm;

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
        database = Realm.getDefaultInstance();
    }

    public void create(Operator<T> operator) {
        database.beginTransaction();
        T object=database.createObject(opClass);
        operator.proceed(object);
        database.commitTransaction();
    }

    public void update(int id, Operator<T> operator){
        database.beginTransaction();
        T object=getById(id);
        operator.proceed(object);
        database.copyToRealmOrUpdate(object);
        database.commitTransaction();
    }

    public void update(T object){
        database.beginTransaction();
        database.copyToRealmOrUpdate(object);
        database.commitTransaction();
    }

    public static interface Operator<T>{
        void proceed(T object);
    }

    public T getById(int id){
        RealmQuery<T> query= getQuery();

        query.equalTo("id", id);

        return query.findFirst();
    }

    public T getFirst(){
        RealmQuery<T> query= getQuery();

        return query.findFirst();
    }

    private RealmQuery<T> getQuery(){
        return (RealmQuery<T>) database.where(opClass);
    }
}
