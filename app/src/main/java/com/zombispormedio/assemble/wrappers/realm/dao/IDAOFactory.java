package com.zombispormedio.assemble.wrappers.realm.dao;


import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public interface IDAOFactory<D extends RealmObject> {

    D create();

}




