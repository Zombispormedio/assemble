package com.zombispormedio.assemble.models.builders;

import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.wrappers.firebase.FirebaseAuthWrapper;

/**
 * Created by Master on 10/07/2016.
 */
public class ModelBuilder {

    public static User createUser(){
        return new User(new FirebaseAuthWrapper());
    }
}
