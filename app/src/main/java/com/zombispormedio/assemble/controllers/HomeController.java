package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.models.factories.ResourceFactory;
import com.zombispormedio.assemble.models.resources.UserResource;
import com.zombispormedio.assemble.rest.Error;
import com.zombispormedio.assemble.views.IHomeView;

/**
 * Created by Master on 10/07/2016.
 */
public class HomeController implements IBaseController {

    private IHomeView ctx;
    private UserResource userResource;
    private ProfileHandler profileHandler;
    private User user;


    public HomeController(IHomeView ctx) {
        this.ctx = ctx;
        userResource = ResourceFactory.createUser();
        user=null;
        profileHandler=new ProfileHandler();
    }

    public void onDrawerOpened() {
        DrawerTitle();


    }

    private void DrawerTitle(){
        String title="";
        if(user!=null){
            if(user.username!=null){
                title=user.username;
            }else{
                if(user.email!=null){
                    title=user.email;
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
        userResource.getProfile(profileHandler);
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



    private class ProfileHandler implements IServiceHandler<User, Error> {
        @Override
        public void onError(Error error) {

        }

        @Override
        public void onSuccess(User result) {
            user=result;
            DrawerTitle();
        }
    }
}
