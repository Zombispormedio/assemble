package com.zombispormedio.assemble.models.resources;


import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.IServiceHandlerWithArgs;
import com.zombispormedio.assemble.services.IAuthService;
import com.zombispormedio.assemble.services.IPersistenceService;

import java.util.TreeMap;

/**
 * Created by Master on 08/07/2016.
 */
public class UserResources {

    private IAuthService auth;
    private IPersistenceService persistence;

    public UserResources(IAuthService auth, IPersistenceService persistence) {
        this.auth=auth;
        this.persistence=persistence;
    }



    public class AccessVerifier{
        private IAuthService auth;
        public AccessVerifier(IAuthService auth0, IServiceHandler listener) {
            auth=auth0;
            auth.checkAccess(listener);

        }

        public void start(){
            auth.startCheckAccess();
        }

        public void stop(){
            auth.stopCheckAccess();
        }
    }

    public AccessVerifier createAccessVerifier(IServiceHandler listener){
        return new AccessVerifier(auth, listener);
    }

    public void login(String email, String password, IServiceHandlerWithArgs<String> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IServiceHandlerWithArgs<String> listener) {
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
