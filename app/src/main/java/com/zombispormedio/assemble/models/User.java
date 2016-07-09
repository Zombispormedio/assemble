package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.controllers.IBaseListener;
import com.zombispormedio.assemble.utils.AuthWrapper;

/**
 * Created by Master on 08/07/2016.
 */
public class User implements IBaseModel {
    private static final String TAG = User.class.getName();
    private AuthWrapper auth;

    public User() {
        auth=new AuthWrapper();
    }



    @Override
    public void create() {
        //TODO
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void remove() {
        //TODO
    }

    public class AccessVerifier{
        private AuthWrapper auth;
        public AccessVerifier(AuthWrapper auth0, IBaseListener<Integer> listener) {
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
}
