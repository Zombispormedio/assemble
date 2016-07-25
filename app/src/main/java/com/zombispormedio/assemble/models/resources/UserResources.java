package com.zombispormedio.assemble.models.resources;



import com.zombispormedio.assemble.handlers.IServiceHandler2;

import com.zombispormedio.assemble.services.IAuthService;
import com.zombispormedio.assemble.services.IPersistenceService;



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

    public void verifyAccess(IServiceHandler2 listener){

    }




    public void login(String email, String password, IServiceHandler2<String, String> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IServiceHandler2<String, String> listener) {
        auth.create(email, password, listener);
    }

    public void create(String email, String name){


    }


    public void signOut(){
        auth.signOut();
    }




}
