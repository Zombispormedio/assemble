package com.zombispormedio.assemble.models.resources;



import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.User;
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




    public void login(String email, String password, IServiceHandler<Result, Error> listener){
        auth.login(email, password, listener);
    }



    public void signin(String email, String password, IServiceHandler<Result, Error> listener) {
        auth.register(email, password, listener);
    }


    public void signOut(final IServiceHandler<Result, Error> handler){
        auth.signOut(handler);
    }

    public void getProfile(final IServiceHandler<User, Error> handler){
        persistence.retrieve(handler);
    }




}
