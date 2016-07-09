package com.zombispormedio.assemble.utils;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.zombispormedio.assemble.controllers.IBaseListener;



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

    public void login(String email, String password, final IBaseListener<String> listener){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            listener.onSuccess(task.getResult().getUser().getUid());

                        }else{
                            listener.onError(task.getException().getMessage());
                        }
                    }
                });
    }


    public void create(String email, String password, final IBaseListener<String> listener){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            listener.onSuccess(task.getResult().getUser().getUid());

                        }else{
                            listener.onError(task.getException().getMessage());
                        }
                    }
                });
    }

    public void signOut(){
        mAuth.signOut();
    }




}
