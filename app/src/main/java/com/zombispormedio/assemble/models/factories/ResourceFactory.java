package com.zombispormedio.assemble.models.factories;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.services.api.AuthAPIService;
import com.zombispormedio.assemble.services.api.MeetingAPIService;
import com.zombispormedio.assemble.services.api.ProfileAPIService;
import com.zombispormedio.assemble.services.api.TeamAPIService;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class ResourceFactory {

    public static UserResource createUserResource() {
        return new UserResource(new AuthAPIService(), new ProfileAPIService());
    }

    public static TeamResource createTeamResource(){
        return new TeamResource(new TeamAPIService());
    }

    public static MeetingResource createMeetingResource(){
        return new MeetingResource(new MeetingAPIService());
    }
}
