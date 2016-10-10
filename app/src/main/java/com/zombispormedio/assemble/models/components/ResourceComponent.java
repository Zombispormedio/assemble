package com.zombispormedio.assemble.models.components;

import com.zombispormedio.assemble.models.modules.PersistenceModule;
import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.models.modules.SubscriptionModule;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Singleton
@Component(modules = {ResourceModule.class, SubscriptionModule.class})
public interface ResourceComponent {

    UserResource provideUserResource();

    ProfileResource provideProfileResource();

    FriendResource provideFriendResource();

    FriendRequestResource provideFriendRequestResource();

    TeamResource provideTeamResource();

    MeetingResource provideMeetingResource();

    ChatResource provideChatResource();

    ProfileSubscription provideProfileSubscription();

    FriendSubscription provideFriendSubscription();

    FriendRequestSubscription provideFriendRequestSubscription();

    TeamSubscription provideTeamSubscription();

    MeetingSubscription provideMeetingSubscription();

    ChatSubscription provideChatSubscription();

    MessageSubscription provideMessageSubscription();
}
