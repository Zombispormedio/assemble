package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.loaders.ChatLoader;
import com.zombispormedio.assemble.models.loaders.FriendLoader;
import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;
import com.zombispormedio.assemble.models.loaders.MeetingLoader;
import com.zombispormedio.assemble.models.loaders.MessageLoader;
import com.zombispormedio.assemble.models.loaders.ProfileLoader;
import com.zombispormedio.assemble.models.loaders.TeamLoader;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 14/10/2016.
 */

@Module
public class LoaderModule extends PersistenceModule {

    @NonNull
    @Provides
    @Singleton
    ProfileLoader provideProfileLoader() {
        return new ProfileLoader(provideProfileAPIService(), provideProfileStorageService());
    }

    @NonNull
    @Provides
    @Singleton
    FriendLoader provideFriendLoader() {
        return new FriendLoader(provideFriendAPIService(), provideFriendStorageService());
    }

    @NonNull
    @Provides
    @Singleton
    FriendRequestLoader provideFriendRequestLoader() {
        return new FriendRequestLoader(provideFriendAPIService(), provideFriendRequestStorageService());
    }

    @NonNull
    @Provides
    @Singleton
    TeamLoader provideTeamLoader() {
        return new TeamLoader(provideTeamAPIService(), provideTeamStorageService());
    }

    @NonNull
    @Provides
    @Singleton
    MeetingLoader provideMeetingLoader() {
        return new MeetingLoader(provideMeetingAPIService(), provideMeetingStorageService());
    }

    @NonNull
    @Provides
    @Singleton
    ChatLoader provideChatLoader() {
        return new ChatLoader(provideChatAPIService(), provideChatStorageService());
    }


    @NonNull
    @Provides
    @Singleton
    MessageLoader provideMessageLoader() {
        return new MessageLoader(provideChatAPIService(), provideMessageStorageService());
    }

}
