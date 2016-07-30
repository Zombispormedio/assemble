package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController implements IBaseController {

    private IHomeView ctx;
    private UserResource userResource;
    private ProfileHandler profileHandler;
    private UserProfile userProfile;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        userResource = ResourceFactory.createUser();
        userProfile =null;
        profileHandler=new ProfileHandler();

        userResource.getProfile(profileHandler);
    }

    public void onDrawerOpened() {
        DrawerTitle();


    }

    private void DrawerTitle(){
        String title="";
        if(userProfile !=null){
            if(userProfile.username!=null){
                title= userProfile.username;
            }else{
                if(userProfile.email!=null){
                    title= userProfile.email;
                }
            }
        }

        ctx.setDrawerTitle(title);
    }

    public void onSettingsMenuItemClick() {
        ctx.goToSettings();
    }


    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {

    }



    @Override
    public void onStop() {

    }


    public void onProfileMenuItemClick(){

        ctx.goToProfile();
    }

    public void onRestart() {
        userResource.getProfile(profileHandler);
    }

    public void onResume() {
        userResource.getProfile(profileHandler);
    }



    private class ProfileHandler implements IServiceHandler<UserProfile, Error> {
        @Override
        public void onError(Error error) {

        }

        @Override
        public void onSuccess(UserProfile result) {
            userProfile =result;
            DrawerTitle();
        }
    }
}
