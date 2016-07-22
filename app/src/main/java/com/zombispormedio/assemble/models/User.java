package com.zombispormedio.assemble.models;


import android.util.Pair;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.listeners.IListener;
import com.zombispormedio.assemble.listeners.IListenerWithArgs;
import com.zombispormedio.assemble.wrappers.IAuthWrapper;
import com.zombispormedio.assemble.wrappers.IPersistenceWrapper;

import java.util.TreeMap;

/**
 * Created by Master on 08/07/2016.
 */
public class User implements IRemovable {

    private IAuthWrapper auth;
    private IPersistenceWrapper persistence;

    public User(IAuthWrapper auth, IPersistenceWrapper persistence) {
        this.auth=auth;
        this.persistence=persistence;
    }



    public class AccessVerifier{
        private IAuthWrapper auth;
        public AccessVerifier(IAuthWrapper auth0, IListener listener) {
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

    public AccessVerifier createAccessVerifier(IListener listener){
        return new AccessVerifier(auth, listener);
    }

    public void login(String email, String password, IListenerWithArgs<String> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IListenerWithArgs<String> listener) {
        auth.create(email, password, listener);
    }

    public void create(String email, String name){
        TreeMap<String, String> map=new TreeMap<String, String>();
        map.put("email", email);

        map.put("name", name);

        Logger.d(map.size());

        persistence.save(auth.getID(), "profile", map);

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


    public static String getNameByEmail(String email){
        return "";
    }
}
