package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 14/10/2016.
 */
@Module
public class ResourceModule extends PersistenceModule {

    @Provides
    @Singleton
    UserResource provideUserResource() {
        return new UserResource(provideAuthAPIService());
    }

    @Provides
    @Singleton
    ProfileResource provideProfileResource() {
        return new ProfileResource(provideProfileAPIService(), provideProfileStorageService());
    }

    @Provides
    @Singleton
    FriendResource provideFriendResource() {
        return new FriendResource(provideFriendAPIService(), provideFriendStorageService());
    }

    @Provides
    @Singleton
    FriendRequestResource provideFriendRequestResource() {
        return new FriendRequestResource(provideFriendAPIService(),provideFriendRequestStorageService());
    }

    @Provides
    @Singleton
    TeamResource provideTeamResource() {
        return new TeamResource(provideTeamAPIService(), provideTeamStorageService());
    }

    @Provides
    @Singleton
    MeetingResource provideMeetingResource() {
        return new MeetingResource(provideMeetingAPIService(), provideMeetingStorageService());
    }

    @Provides
    @Singleton
    ChatResource provideChatResource() {
        return new ChatResource(provideChatAPIService(), provideChatStorageService(), provideMessageStorageService());
    }

}
