package com.zombispormedio.assemble.models.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.editors.ProfileEditor;
import com.zombispormedio.assemble.net.Error;

import java.io.File;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public interface IProfileService {

    void retrieve(final IServiceHandler<UserProfile, Error> handler);

    void changeAvatar(File file, final IServiceHandler<UserProfile, Error> handler);

    void update(ProfileEditor profile, final IServiceHandler<UserProfile, Error> handler);

}
