package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.loaders.ChatLoader;
import com.zombispormedio.assemble.models.loaders.FriendLoader;
import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;
import com.zombispormedio.assemble.models.loaders.MeetingLoader;
import com.zombispormedio.assemble.models.loaders.MessageLoader;
import com.zombispormedio.assemble.models.loaders.ProfileLoader;
import com.zombispormedio.assemble.models.loaders.TeamLoader;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.models.services.api.AuthAPIService;
import com.zombispormedio.assemble.models.services.api.ChatAPIService;
import com.zombispormedio.assemble.models.services.api.FriendAPIService;
import com.zombispormedio.assemble.models.services.api.MeetingAPIService;
import com.zombispormedio.assemble.models.services.api.ProfileAPIService;
import com.zombispormedio.assemble.models.services.api.TeamAPIService;
import com.zombispormedio.assemble.models.services.storage.ChatStorageService;
import com.zombispormedio.assemble.models.services.storage.FriendRequestStorageService;
import com.zombispormedio.assemble.models.services.storage.FriendStorageService;
import com.zombispormedio.assemble.models.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.models.services.storage.MessageStorageService;
import com.zombispormedio.assemble.models.services.storage.ProfileStorageService;
import com.zombispormedio.assemble.models.services.storage.TeamStorageService;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.wrappers.realm.dao.ChatDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.FriendProfileDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.FriendRequestProfileDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.MeetingDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.MessageDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.TeamDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.UserProfileDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 10/10/2016.
 */
@Module
public class PersistenceModule extends StorageModule{

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

}
