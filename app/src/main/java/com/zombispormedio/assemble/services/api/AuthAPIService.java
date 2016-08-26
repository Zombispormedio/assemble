package com.zombispormedio.assemble.services.api;


import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;

import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.JsonBinder;
import com.zombispormedio.assemble.rest.Result;
import com.zombispormedio.assemble.rest.responses.DefaultResponse;
import com.zombispormedio.assemble.services.IAuthService;

import java.io.IOException;


/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public class AuthAPIService implements IAuthService {

    private APIConfiguration api;

    public AuthAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void checkAccess(final IServiceHandler<Result, Error> handler) {

        api.RestWithAuth("/check_user")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            DefaultResponse res = JsonBinder.toDefaultResponse(args[0]);
                            if (res.success) {
                                handler.onSuccess(res.result);
                            } else {
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .get();

    }


    @Override
    public void login(String email, String password, final IServiceHandler<Result, Error> handler) {
        Auth user = new Auth(email, password);
        api.Rest("/login")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            DefaultResponse res = JsonBinder.toDefaultResponse(args[0]);
                            if (res.success) {
                                handler.onSuccess(res.result);
                            } else {
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .post(JsonBinder.fromAuth(user));
    }

    @Override
    public void register(String email, String password, final IServiceHandler<Result, Error> handler) {
        Auth user = new Auth(email, password);
        api.Rest("/signup")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            DefaultResponse res = JsonBinder.toDefaultResponse(args[0]);
                            if (res.success) {
                                handler.onSuccess(res.result);
                            } else {
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .post(JsonBinder.fromAuth(user));
    }

    @Override
    public void signOut(final IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/signout")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            DefaultResponse res = JsonBinder.toDefaultResponse(args[0]);
                            if (res.success) {
                                handler.onSuccess(res.result);
                            } else {
                                handler.onError(res.error);
                            }

                        } catch (IOException e) {
                            handler.onError(new Error(e.getMessage()));
                        }

                    }
                })
                .get();
    }


}
