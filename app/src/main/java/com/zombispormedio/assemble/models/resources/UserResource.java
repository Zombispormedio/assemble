package com.zombispormedio.assemble.models.resources;



import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.services.IAuthService;
import com.zombispormedio.assemble.services.IPersistenceService;



/**
 * Created by Master on 08/07/2016.
 */
public class UserResource {

    private IAuthService auth;
    private IPersistenceService persistence;

    public UserResource(IAuthService auth, IPersistenceService persistence) {
        this.auth=auth;
        this.persistence=persistence;
    }

    public void checkAccess(IServiceHandler<Result,Error> listener){
        auth.checkAccess(listener);
    }




    public void login(String email, String password, IServiceHandler<String, String> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IServiceHandler<String, String> listener) {
        auth.register(email, password, listener);
    }

    public void create(String email, String name){


    }


    public void signOut(){
        auth.signOut();
    }




}
