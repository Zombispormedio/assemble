package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.services.api.AuthAPIService;
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
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ResourceFactory {

    public static UserResource createUserResource() {
        return new UserResource(new AuthAPIService(), new ProfileAPIService());
    }

    public static TeamResource createTeamResource(){
        return new TeamResource(new TeamAPIService(), new TeamStorageService());
    }

    public static MeetingResource createMeetingResource(){
        return new MeetingResource(new MeetingAPIService(), new MeetingStorageService());
    }

    public static ChatResource createChatResource(){
        return new ChatResource(new ChatAPIService(), new ChatStorageService());
    }

    public static ProfileResource createProfileResource(){
        return new ProfileResource(new ProfileStorageService());
    }

    public static FriendResource createFriendResource(){
        return new FriendResource(new FriendStorageService());
    }
    public static FriendRequestResource createFriendRequestResource(){
        return new FriendRequestResource(new FriendRequestStorageService());
    }


}
