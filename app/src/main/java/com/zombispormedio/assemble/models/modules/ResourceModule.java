package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.services.api.AuthAPIService;
import com.zombispormedio.assemble.services.api.ChatAPIService;
import com.zombispormedio.assemble.services.api.FriendAPIService;
import com.zombispormedio.assemble.services.api.MeetingAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.api.TeamAPIService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.FriendRequestStorageService;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;
import com.zombispormedio.assemble.services.storage.TeamStorageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Module
public class ResourceModule {

    @Provides
    @Singleton
    AuthAPIService provideAuthAPIService() {
        return new AuthAPIService();
    }

    @Provides
    @Singleton
    ProfileAPIService provideProfileAPIService() {
        return new ProfileAPIService();
    }

    @Provides
    @Singleton
    FriendAPIService provideFriendAPIService() {
        return new FriendAPIService();
    }

    @Provides
    @Singleton
    TeamAPIService provideTeamAPIService() {
        return new TeamAPIService();
    }

    @Provides
    @Singleton
    MeetingAPIService provideMeetingAPIService() {
        return new MeetingAPIService();
    }

    @Provides
    @Singleton
    ChatAPIService provideChatAPIService() {
        return new ChatAPIService();
    }


    @Provides
    @Singleton
    ProfileStorageService provideProfileStorageService() {
        return new ProfileStorageService();
    }

    @Provides
    @Singleton
    FriendStorageService provideFriendStorageService() {
        return new FriendStorageService();
    }

    @Provides
    @Singleton
    FriendRequestStorageService provideRequestStorageService() {
        return new FriendRequestStorageService();
    }

    @Provides
    @Singleton
    TeamStorageService provideTeamStorageService() {
        return new TeamStorageService();
    }

    @Provides
    @Singleton
    MeetingStorageService provideMeetingStorageService() {
        return new MeetingStorageService();
    }

    @Provides
    @Singleton
    ChatStorageService provideChatStorageService() {
        return new ChatStorageService();
    }


    @Provides
    @Singleton
    UserResource provideUserResource() {
        return new UserResource(new AuthAPIService());
    }

    @Provides
    @Singleton
    ProfileResource provideProfileResource() {
        return new ProfileResource(new ProfileAPIService(), new ProfileStorageService());
    }

    @Provides
    @Singleton
    FriendResource provideFriendResource() {
        return new FriendResource(provideFriendAPIService(), new FriendStorageService());
    }

    @Provides
    @Singleton
    FriendRequestResource provideFriendRequestResource() {
        return new FriendRequestResource(provideFriendAPIService(),new FriendRequestStorageService());
    }

    @Provides
    @Singleton
    TeamResource provideTeamResource() {
        return new TeamResource(new TeamAPIService(), new TeamStorageService());
    }

    @Provides
    @Singleton
    MeetingResource provideMeetingResource() {
        return new MeetingResource(new MeetingAPIService(), new MeetingStorageService());
    }

    @Provides
    @Singleton
    ChatResource provideChatResource() {
        return new ChatResource(new ChatAPIService(), new ChatStorageService());
    }

}
