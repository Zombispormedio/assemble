package com.zombispormedio.assemble.models.modules;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.services.api.AuthAPIService;
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
    LocalStorage<MeetingDAO, Team> provideMeetingLocalStorage() {
        return new LocalStorage<>(MeetingDAO.class, provideMeetingDAOFactory());
    }


    @Provides
    @Singleton
    LocalStorage<ChatDAO, Team> provideChatLocalStorage() {
        return new LocalStorage<>(ChatDAO.class, provideChatDAOFactory());
    }

    @Provides
    @Singleton
    LocalStorage<MessageDAO, Team> provideMessageLocalStorage() {
        return new LocalStorage<>(MessageDAO.class, provideMessageDAOFactory());
    }


    /*************************************************************************************************************************/
}
