package com.zombispormedio.assemble.utils;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.zombispormedio.assemble.controllers.IBaseListener;



/**
 * Created by Master on 08/07/2016.
 */
public class AuthWrapper {

    private static final String TAG = AuthWrapper.class.getName();

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener authListener;

    public AuthWrapper() {
        mAuth=FirebaseAuth.getInstance();
        authListener=null;
    }

    public void initCheckAccess(final IBaseListener<Integer> listener){
        authListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    listener.onSuccess(0);
                }else{
                    listener.onError(1);
                }


            }
        };
    }

    public void startCheckAccess(){
        mAuth.addAuthStateListener(authListener);
    }

    public void stopCheckAccess(){
        if (authListener!=null){
            mAuth.removeAuthStateListener(authListener);
        }
    }




}
