package com.zombispormedio.assemble.models.modules;


import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.loaders.ChatLoader;
import com.zombispormedio.assemble.models.loaders.FriendLoader;
import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;
import com.zombispormedio.assemble.models.loaders.MeetingLoader;
import com.zombispormedio.assemble.models.loaders.MessageLoader;
import com.zombispormedio.assemble.models.loaders.ProfileLoader;
import com.zombispormedio.assemble.models.loaders.TeamLoader;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.services.api.ChatAPIService;
import com.zombispormedio.assemble.services.api.FriendAPIService;
import com.zombispormedio.assemble.services.api.MeetingAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.api.TeamAPIService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.FriendRequestStorageService;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.services.storage.MessageStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;
import com.zombispormedio.assemble.services.storage.TeamStorageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Module
public class SubscriptionModule {

    @Provides
    @Singleton
    ProfileLoader provideProfileLoader() {
        return new ProfileLoader(new ProfileAPIService(), new ProfileStorageService());
    }

    @Provides
    @Singleton
    FriendLoader provideFriendLoader() {
        return new FriendLoader(new FriendAPIService(), new FriendStorageService());
    }

    @Provides
    @Singleton
    FriendRequestLoader provideFriendRequestLoader() {
        return new FriendRequestLoader(new FriendAPIService(), new FriendRequestStorageService());
    }

    @Provides
    @Singleton
    TeamLoader provideTeamLoader() {
        return new TeamLoader(new TeamAPIService(), new TeamStorageService());
    }

    @Provides
    @Singleton
    MeetingLoader provideMeetingLoader() {
        return new MeetingLoader(new MeetingAPIService(), new MeetingStorageService());
    }

    @Provides
    @Singleton
    ChatLoader provideChatLoader() {
        return new ChatLoader(new ChatAPIService(), new ChatStorageService());
    }


    @Provides
    @Singleton
    MessageLoader provideMessageLoader() {
        return new MessageLoader(new ChatAPIService(), new MessageStorageService());
    }

    @Provides
    @Singleton
    ProfileSubscription provideProfileSubscription() {
        return new  ProfileSubscription(provideProfileLoader());
    }

    @Provides
    @Singleton
    FriendSubscription provideFriendSubscription() {
        return new  FriendSubscription(provideFriendLoader());
    }

    @Provides
    @Singleton
    FriendRequestSubscription provideFriendRequestSubscription() {
        return new  FriendRequestSubscription(provideFriendRequestLoader());
    }

    @Provides
    @Singleton
    TeamSubscription provideTeamSubscription() {
        return new  TeamSubscription(provideTeamLoader());
    }

    @Provides
    @Singleton
    MeetingSubscription provideMeetingSubscription() {
        return new  MeetingSubscription(provideMeetingLoader());
    }

    @Provides
    @Singleton
    ChatSubscription provideChatSubscription() {
        return new  ChatSubscription(provideChatLoader());
    }

    @Provides
    @Singleton
    MessageSubscription provideMessageSubscription() {
        return new  MessageSubscription(provideMessageLoader());
    }
}
