package com.zombispormedio.assemble.services;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.rest.Result;

import java.io.File;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileService {

    void retrieve(final IServiceHandler<UserProfile, Error> handler);

    void changeAvatar(File file, final IServiceHandler<UserProfile, Error> handler);

}
