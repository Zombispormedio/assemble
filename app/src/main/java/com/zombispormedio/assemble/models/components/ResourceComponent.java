package com.zombispormedio.assemble.models.components;

import com.zombispormedio.assemble.models.modules.ResourceModule;
import com.zombispormedio.assemble.models.resources.ChatResource;
import com.zombispormedio.assemble.models.resources.ProfileResource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xavier Serrano on 18/09/2016.
 */
@Singleton
@Component(modules = {ResourceModule.class})
public interface ResourceComponent {

    ProfileResource provideProfileResource();
    ChatResource provideChatResource();
}
