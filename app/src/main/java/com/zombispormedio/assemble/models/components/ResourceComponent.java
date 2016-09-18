package com.zombispormedio.assemble.models.components;

import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.FriendRequestResource;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.models.resources.UserResource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Singleton
@Component(modules = {ResourceModule.class})
public interface ResourceComponent {

    UserResource provideUserResource();

    ProfileResource provideProfileResource();

    FriendResource provideFriendResource();

    FriendRequestResource provideFriendRequestResource();

    TeamResource provideTeamResource();

    MeetingResource provideMeetingResource();

    ChatResource provideChatResource();
}
