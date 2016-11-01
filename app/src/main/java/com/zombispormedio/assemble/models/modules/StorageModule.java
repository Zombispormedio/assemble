package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.services.storage.ChatStorageService;
import com.zombispormedio.assemble.models.services.storage.FriendRequestStorageService;
import com.zombispormedio.assemble.models.services.storage.FriendStorageService;
import com.zombispormedio.assemble.models.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.models.services.storage.MessageStorageService;
import com.zombispormedio.assemble.models.services.storage.ProfileStorageService;
import com.zombispormedio.assemble.models.services.storage.TeamStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;
import com.zombispormedio.assemble.wrappers.realm.dao.ChatDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.FriendProfileDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.FriendRequestProfileDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.MeetingDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.MessageDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.TeamDAO;
import com.zombispormedio.assemble.wrappers.realm.dao.UserProfileDAO;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xavier Serrano on 14/10/2016.
 */
@Module
public class StorageModule {

    /************************************************ DAOFactories *************************************************************/

    @NonNull
    @Provides
    @Singleton
    UserProfileDAO.Factory provideUserProfileDAOFactory() {
        return new UserProfileDAO.Factory();
    }


    @NonNull
    @Provides
    @Singleton
    FriendProfileDAO.Factory provideFriendProfileDAOFactory() {
        return new FriendProfileDAO.Factory();
    }


    @NonNull
    @Provides
    @Singleton
    FriendRequestProfileDAO.Factory provideFriendRequestProfileDAOFactory() {
        return new FriendRequestProfileDAO.Factory();
    }


    @NonNull
    @Provides
    @Singleton
    TeamDAO.Factory provideTeamDAOFactory() {
        return new TeamDAO.Factory();
    }

    @NonNull
    @Provides
    @Singleton
    MeetingDAO.Factory provideMeetingDAOFactory() {
        return new MeetingDAO.Factory();
    }

    @NonNull
    @Provides
    @Singleton
    ChatDAO.Factory provideChatDAOFactory() {
        return new ChatDAO.Factory();
    }

    @NonNull
    @Provides
    @Singleton
    MessageDAO.Factory provideMessageDAOFactory() {
        return new MessageDAO.Factory();
    }

    /*************************************************************************************************************************/

    /************************************************ LocalStorages *************************************************************/

    @NonNull
    @Provides
    @Singleton
    LocalStorage<UserProfileDAO, UserProfile> provideProfileLocalStorage() {
        return new LocalStorage<>(UserProfileDAO.class, provideUserProfileDAOFactory());
    }


    @NonNull
    @Provides
    @Singleton
    LocalStorage<FriendProfileDAO, FriendProfile> provideFriendLocalStorage() {
        return new LocalStorage<>(FriendProfileDAO.class, provideFriendProfileDAOFactory());
    }

    @NonNull
    @Provides
    @Singleton
    LocalStorage<FriendRequestProfileDAO, FriendRequestProfile> provideFriendRequestLocalStorage() {
        return new LocalStorage<>(FriendRequestProfileDAO.class, provideFriendRequestProfileDAOFactory());
    }


    @NonNull
    @Provides
    @Singleton
    LocalStorage<TeamDAO, Team> provideTeamLocalStorage() {
        return new LocalStorage<>(TeamDAO.class, provideTeamDAOFactory());
    }

    @NonNull
    @Provides
    @Singleton
    LocalStorage<MeetingDAO, Meeting> provideMeetingLocalStorage() {
        return new LocalStorage<>(MeetingDAO.class, provideMeetingDAOFactory());
    }


    @NonNull
    @Provides
    @Singleton
    LocalStorage<ChatDAO, Chat> provideChatLocalStorage() {
        return new LocalStorage<>(ChatDAO.class, provideChatDAOFactory());
    }

    @NonNull
    @Provides
    @Singleton
    LocalStorage<MessageDAO, Message> provideMessageLocalStorage() {
        return new LocalStorage<>(MessageDAO.class, provideMessageDAOFactory());
    }

    /*************************************************************************************************************************/


    /************************************************ StorageServices *************************************************************/

    @NonNull
    @Provides
    @Singleton
    ProfileStorageService provideProfileStorageService() {
        return new ProfileStorageService(provideProfileLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    FriendStorageService provideFriendStorageService() {
        return new FriendStorageService(provideFriendLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    FriendRequestStorageService provideFriendRequestStorageService() {
        return new FriendRequestStorageService(provideFriendRequestLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    TeamStorageService provideTeamStorageService() {
        return new TeamStorageService(provideTeamLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    MeetingStorageService provideMeetingStorageService() {
        return new MeetingStorageService(provideMeetingLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    ChatStorageService provideChatStorageService() {
        return new ChatStorageService(provideChatLocalStorage());
    }

    @NonNull
    @Provides
    @Singleton
    MessageStorageService provideMessageStorageService() {
        return new MessageStorageService(provideMessageLocalStorage());
    }
}
