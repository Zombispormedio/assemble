package com.zombispormedio.assemble.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.zombispormedio.assemble.controllers.IBaseListener;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Created by Master on 08/07/2016.
 */
public class AuthWrapper {

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener authListener;

    public AuthWrapper() {
        mAuth=FirebaseAuth.getInstance();
        authListener=null;
    }

    public void initVerifyAccess(IBaseListener<String> listener){

    }

    public void startCheckAccess(){

    }

    public void stopCheckAccess(){

    }




}
