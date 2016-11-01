package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.services.api.AuthAPIService;
import com.zombispormedio.assemble.models.services.api.ChatAPIService;
import com.zombispormedio.assemble.models.services.api.FriendAPIService;
import com.zombispormedio.assemble.models.services.api.MeetingAPIService;
import com.zombispormedio.assemble.models.services.api.ProfileAPIService;
import com.zombispormedio.assemble.models.services.api.TeamAPIService;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 10/10/2016.
 */
@Module
public class PersistenceModule extends StorageModule {

    @NonNull
    @Provides
    @Singleton
    AuthAPIService provideAuthAPIService() {
        return new AuthAPIService();
    }

    @NonNull
    @Provides
    @Singleton
    ProfileAPIService provideProfileAPIService() {
        return new ProfileAPIService();
    }

    @NonNull
    @Provides
    @Singleton
    FriendAPIService provideFriendAPIService() {
        return new FriendAPIService();
    }

    @NonNull
    @Provides
    @Singleton
    TeamAPIService provideTeamAPIService() {
        return new TeamAPIService();
    }

    @NonNull
    @Provides
    @Singleton
    MeetingAPIService provideMeetingAPIService() {
        return new MeetingAPIService();
    }

    @NonNull
    @Provides
    @Singleton
    ChatAPIService provideChatAPIService() {
        return new ChatAPIService();
    }

}
