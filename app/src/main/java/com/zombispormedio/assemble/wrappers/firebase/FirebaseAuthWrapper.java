package com.zombispormedio.assemble.wrappers.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.zombispormedio.assemble.listeners.IListener;
import com.zombispormedio.assemble.listeners.IListenerWithArgs;
import com.zombispormedio.assemble.wrappers.IAuthWrapper;


/**
 * Created by Master on 08/07/2016.
 */
public class FirebaseAuthWrapper  implements IAuthWrapper {



    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener authListener;

    public FirebaseAuthWrapper() {
        mAuth=FirebaseAuth.getInstance();
        authListener=null;
    }

    public void initCheckAccess(final IListener listener){
        authListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    listener.onSuccess();
                }else{
                    listener.onError();
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

    public void login(String email, String password, final IListenerWithArgs<String> listener){
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


    public void create(String email, String password, final IListenerWithArgs<String> listener){
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

    @Override
    public String getValue(String field) {
        String value="";

        if(mAuth.getCurrentUser()!=null) {
            switch (field) {
                case "email": value=mAuth.getCurrentUser().getEmail();
                    break;
            }

        }

        return value;
    }

    @Override
    public String getID() {
        return mAuth.getCurrentUser().getUid();
    }


}
