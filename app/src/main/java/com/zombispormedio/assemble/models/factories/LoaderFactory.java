package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.loaders.ChatLoader;
import com.zombispormedio.assemble.models.loaders.FriendLoader;
import com.zombispormedio.assemble.models.loaders.FriendRequestLoader;
import com.zombispormedio.assemble.models.loaders.MeetingLoader;
import com.zombispormedio.assemble.models.loaders.ProfileLoader;
import com.zombispormedio.assemble.models.loaders.TeamLoader;
import com.zombispormedio.assemble.services.api.ChatAPIService;
import com.zombispormedio.assemble.services.api.MeetingAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.api.TeamAPIService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.FriendRequestStorageService;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.MeetingStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;
import com.zombispormedio.assemble.services.storage.TeamStorageService;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class LoaderFactory {

    public static ProfileLoader createProfileLoader(){
        return new ProfileLoader(new ProfileAPIService(), new ProfileStorageService());
    }

    public static ChatLoader createChatLoader(){
        return new ChatLoader(new ChatAPIService(), new ChatStorageService());
    }

    public static FriendLoader createFriendLoader(){
        return new FriendLoader(new ProfileAPIService(), new FriendStorageService());
    }

    public static FriendRequestLoader createFriendRequestLoader(){
        return new FriendRequestLoader(new ProfileAPIService(), new FriendRequestStorageService());
    }

    public static MeetingLoader createMeetingLoader(){
        return new MeetingLoader(new MeetingAPIService(), new MeetingStorageService());
    }

    public static TeamLoader createTeamLoader(){
        return new TeamLoader(new TeamAPIService(), new TeamStorageService());
    }

}
