package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.controllers.IBaseListener;
import com.zombispormedio.assemble.wrappers.IAuthWrapper;
import com.zombispormedio.assemble.wrappers.firebase.FirebaseAuthWrapper;

/**
 * Created by Master on 08/07/2016.
 */
public class User implements IRemovable {

    private IAuthWrapper auth;

    public User(IAuthWrapper auth) {
        this.auth=auth;
    }



    public class AccessVerifier{
        private IAuthWrapper auth;
        public AccessVerifier(IAuthWrapper auth0, IBaseListener<Integer> listener) {
            auth=auth0;
            auth.initCheckAccess(listener);

        }

        public void start(){
            auth.startCheckAccess();
        }

        public void stop(){
            auth.stopCheckAccess();
        }
    }

    public AccessVerifier createAccessVerifier(IBaseListener<Integer> listener){
        return new AccessVerifier(auth, listener);
    }

    public void login(String email, String password, IBaseListener<String> listener){
        auth.login(email, password, listener);
    }



    public void create(String email, String password, IBaseListener<String> listener) {
        auth.create(email, password, listener);
    }

    public void signOut(){
        auth.signOut();
    }


    @Override
    public void remove() {
        //TODO
    }

    public String getValue(String field) {
        return auth.getValue(field);
    }

    public String getEmail() {
        return auth.getValue("email");
    }
}
