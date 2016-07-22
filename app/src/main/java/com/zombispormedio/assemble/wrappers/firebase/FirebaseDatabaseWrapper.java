package com.zombispormedio.assemble.wrappers.firebase;

import android.util.Pair;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.wrappers.IPersistenceWrapper;


import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Master on 17/07/2016.
 */
public class FirebaseDatabaseWrapper implements IPersistenceWrapper {


   private FirebaseDatabase database;


    public FirebaseDatabaseWrapper() {
        database=FirebaseDatabase.getInstance();
    }


    @Override
    public void save(String userID, String table, Object value) {

        database.getReference(table).child(userID).setValue(value);
    }

    public void save(String userID, String table,  TreeMap<String, String> values) {
        DatabaseReference ref=database.getReference(table).child(userID);

        for(Map.Entry<String, String> v : values.entrySet()){

            ref.child(v.getKey()).setValue(v.getValue());

        }

    }

}
