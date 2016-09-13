package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.loaders.ProfileLoader;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class LoaderFactory {

    public static ProfileLoader createProfileLoader(){
        return new ProfileLoader(new ProfileAPIService(), new ProfileStorageService());
    }

}
