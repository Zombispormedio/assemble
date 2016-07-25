package com.zombispormedio.assemble.services.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zombispormedio.assemble.services.IPersistenceService;


import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Master on 17/07/2016.
 */
public class FirebaseDatabaseService implements IPersistenceService {


   private FirebaseDatabase database;


    public FirebaseDatabaseService() {
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
