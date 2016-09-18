package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.services.api.ChatAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Module
public class ResourceModule {

    @Provides @Singleton
    ProfileAPIService provideProfileAPIService(){
        return new ProfileAPIService();
    }

    @Provides @Singleton
    ChatAPIService provideChatAPIService(){
        return new ChatAPIService();
    }


    @Provides @Singleton
    ProfileStorageService provideProfileStorageService(){
        return new ProfileStorageService();
    }

    @Provides @Singleton
    ChatStorageService provideChatStorageService(){
        return new ChatStorageService();
    }


    @Provides @Singleton
    ProfileResource provideProfileResource(){
        return new ProfileResource(new ProfileAPIService(), new ProfileStorageService());
    }

    @Provides @Singleton
    ChatResource provideChatResource(){
        return new ChatResource(new ChatAPIService(), new ChatStorageService());
    }

}
