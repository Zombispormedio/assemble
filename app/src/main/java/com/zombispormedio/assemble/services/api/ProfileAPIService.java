package com.zombispormedio.assemble.services.api;


import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;

import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.FileBody;
import com.zombispormedio.assemble.net.JsonBinder;

import com.zombispormedio.assemble.net.responses.FriendRequestsResponse;
import com.zombispormedio.assemble.net.responses.FriendsResponse;
import com.zombispormedio.assemble.net.responses.ProfileResponse;
import com.zombispormedio.assemble.services.IProfileService;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 27/07/2016.
 */
public class ProfileAPIService implements IProfileService {

    private APIConfiguration api;

    public ProfileAPIService() {
        api = APIConfiguration.getInstance();
    }

    @Override
    public void retrieve(final IServiceHandler<UserProfile, Error> handler) {
        api.RestWithAuth("/profile")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            ProfileResponse res = JsonBinder.toProfileResponse(args[0]);
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
    public void changeAvatar(File file, final IServiceHandler<UserProfile, Error> handler) {
        api.RestWithAuth("/profile/avatar")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            ProfileResponse res = JsonBinder.toProfileResponse(args[0]);
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
                .patch(new FileBody(file, "image/*", "avatar", file.getName()));
    }

    @Override
    public void update(EditProfile profile, final IServiceHandler<UserProfile, Error> handler) {
        api.RestWithAuth("/profile")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            ProfileResponse res = JsonBinder.toProfileResponse(args[0]);
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
                .put(JsonBinder.fromEditProfile(profile));
    }

    @Override
    public void getFriends(final IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        api.RestWithAuth("/friends")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            FriendsResponse res = JsonBinder.toFriendsResponse(args[0]);

                            if (res.success) {
                                handler.onSuccess(res.getResult());
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
    public void getFriendRequests(final IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {
        api.RestWithAuth("/friend_requests")
                .handler(new IPromiseHandler() {
                    @Override
                    public void onSuccess(String... args) {
                        try {
                            FriendRequestsResponse res = JsonBinder.toFriendRequestsResponse(args[0]);

                            if (res.success) {
                                handler.onSuccess(res.getResult());
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
