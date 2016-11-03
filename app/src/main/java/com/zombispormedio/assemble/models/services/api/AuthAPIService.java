package com.zombispormedio.assemble.models.services.api;


import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.editors.GCMEditor;
import com.zombispormedio.assemble.models.services.interfaces.IAuthService;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.JsonBinder;
import com.zombispormedio.assemble.network.Result;


/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public class AuthAPIService implements IAuthService {

    private final APIConfiguration api;

    public AuthAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void checkAccess(final IServiceHandler<Result, Error> handler) {

        api.RestWithAuth("/check_user")
                .handler(DeferUtils.defer(handler))
                .get();

    }

    @Override
    public void login(Auth auth, final IServiceHandler<Result, Error> handler) {
        api.Rest("/login")
                .handler(DeferUtils.defer(handler))
                .post(JsonBinder.fromAuth(auth));
    }

    @Override
    public void register(Auth auth, final IServiceHandler<Result, Error> handler) {
        api.Rest("/signup")
                .handler(DeferUtils.defer(handler))
                .post(JsonBinder.fromAuth(auth));
    }

    @Override
    public void signOut(final IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/signout")
                .handler(DeferUtils.defer(handler))
                .get();
    }

    @Override
    public void refreshGCM(String gcmToken, IServiceHandler<Result, Error> handler) {
        api.RestWithAuth("/gcm")
                .handler(DeferUtils.defer(handler))
                .patch(JsonBinder.fromEditGCM(new GCMEditor(gcmToken)));
    }


}
