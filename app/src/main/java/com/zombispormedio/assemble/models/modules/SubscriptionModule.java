package com.zombispormedio.assemble.models.modules;


import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Module
public class SubscriptionModule extends LoaderModule {

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
