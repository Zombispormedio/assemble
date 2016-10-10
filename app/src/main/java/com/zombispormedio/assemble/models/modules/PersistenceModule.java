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
public class PersistenceModule {

    /************************************************DAOFactories*************************************************************/

    @Provides
    @Singleton
    UserProfileDAO.Factory provideUserProfileDAOFactory() {
        return new UserProfileDAO.Factory();
    }


    @Provides
    @Singleton
    FriendProfileDAO.Factory provideFriendProfileDAOFactory() {
        return new FriendProfileDAO.Factory();
    }


    @Provides
    @Singleton
    FriendRequestProfileDAO.Factory provideFriendRequestProfileDAOFactory() {
        return new FriendRequestProfileDAO.Factory();
    }


    @Provides
    @Singleton
    TeamDAO.Factory provideTeamDAOFactory() {
        return new TeamDAO.Factory();
    }

    @Provides
    @Singleton
    MeetingDAO.Factory provideMeetingDAOFactory() {
        return new MeetingDAO.Factory();
    }

    @Provides
    @Singleton
    ChatDAO.Factory provideChatDAOFactory() {
        return new ChatDAO.Factory();
    }

    @Provides
    @Singleton
    MessageDAO.Factory provideMessageDAOFactory() {
        return new MessageDAO.Factory();
    }


    /*************************************************************************************************************************/

    /************************************************LocalStorages*************************************************************/

    @Provides
    @Singleton
    LocalStorage<UserProfileDAO, UserProfile> provideProfileLocalStorage() {
        return new LocalStorage<>(UserProfileDAO.class, provideUserProfileDAOFactory());
    }


    @Provides
    @Singleton
    LocalStorage<FriendProfileDAO, FriendProfile> provideFriendLocalStorage() {
        return new LocalStorage<>(FriendProfileDAO.class, provideFriendProfileDAOFactory());
    }

    @Provides
    @Singleton
    LocalStorage<FriendRequestProfileDAO, FriendRequestProfile> provideFriendRequestLocalStorage() {
        return new LocalStorage<>(FriendRequestProfileDAO.class, provideFriendRequestProfileDAOFactory());
    }


    @Provides
    @Singleton
    LocalStorage<TeamDAO, Team> provideTeamLocalStorage() {
        return new LocalStorage<>(TeamDAO.class, provideTeamDAOFactory());
    }

    @Provides
    @Singleton
    LocalStorage<MeetingDAO, Meeting> provideMeetingLocalStorage() {
        return new LocalStorage<>(MeetingDAO.class, provideMeetingDAOFactory());
    }


    @Provides
    @Singleton
    LocalStorage<ChatDAO, Chat> provideChatLocalStorage() {
        return new LocalStorage<>(ChatDAO.class, provideChatDAOFactory());
    }

    @Provides
    @Singleton
    LocalStorage<MessageDAO, Message> provideMessageLocalStorage() {
        return new LocalStorage<>(MessageDAO.class, provideMessageDAOFactory());
    }


    /*************************************************************************************************************************/


    /************************************************StorageServices*************************************************************/

    @Provides
    @Singleton
    ProfileStorageService provideProfileStorageService() {
        return new ProfileStorageService(provideProfileLocalStorage());
    }

    @Provides
    @Singleton
    FriendStorageService provideFriendStorageService() {
        return new FriendStorageService(provideFriendLocalStorage());
    }

    @Provides
    @Singleton
    FriendRequestStorageService provideFriendRequestStorageService() {
        return new FriendRequestStorageService(provideFriendRequestLocalStorage());
    }

    @Provides
    @Singleton
    TeamStorageService provideTeamStorageService() {
        return new TeamStorageService(provideTeamLocalStorage());
    }

    @Provides
    @Singleton
    MeetingStorageService provideMeetingStorageService() {
        return new MeetingStorageService(provideMeetingLocalStorage());
    }

    @Provides
    @Singleton
    ChatStorageService provideChatStorageService() {
        return new ChatStorageService(provideChatLocalStorage());
    }

    @Provides
    @Singleton
    MessageStorageService provideMessageStorageService() {
        return new MessageStorageService(provideMessageLocalStorage());
    }

    /*************************************************************************************************************************/

    /************************************************APIServices*************************************************************/

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

    /*************************************************************************************************************************/

    /************************************************Resources*************************************************************/

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

    /*************************************************************************************************************************/


    /************************************************Loaders************************************************************/
    @Provides
    @Singleton
    ProfileLoader provideProfileLoader() {
        return new ProfileLoader(provideProfileAPIService(), provideProfileStorageService());
    }

    @Provides
    @Singleton
    FriendLoader provideFriendLoader() {
        return new FriendLoader(provideFriendAPIService(), provideFriendStorageService());
    }

    @Provides
    @Singleton
    FriendRequestLoader provideFriendRequestLoader() {
        return new FriendRequestLoader(provideFriendAPIService(), provideFriendRequestStorageService());
    }

    @Provides
    @Singleton
    TeamLoader provideTeamLoader() {
        return new TeamLoader(provideTeamAPIService(), provideTeamStorageService());
    }

    @Provides
    @Singleton
    MeetingLoader provideMeetingLoader() {
        return new MeetingLoader(provideMeetingAPIService(), provideMeetingStorageService());
    }

    @Provides
    @Singleton
    ChatLoader provideChatLoader() {
        return new ChatLoader(provideChatAPIService(),provideChatStorageService());
    }


    @Provides
    @Singleton
    MessageLoader provideMessageLoader() {
        return new MessageLoader(provideChatAPIService(), provideMessageStorageService());
    }

    /*************************************************************************************************************************/

    /************************************************Subscriptions***********************************************************/
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
