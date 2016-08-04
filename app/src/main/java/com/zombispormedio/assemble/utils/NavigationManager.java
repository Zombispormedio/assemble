package com.zombispormedio.assemble.utils;

import android.content.Context;
import android.content.Intent;


import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.activities.LoginActivity;
import com.zombispormedio.assemble.activities.MainActivity;
import com.zombispormedio.assemble.activities.ProfileActivity;
import com.zombispormedio.assemble.activities.RegisterActivity;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.activities.UpdateProfileActivity;

/**
 * Created by Xavier Serrano on 25/06/2016.
 */
public final class NavigationManager {
    private Context ctx;

    public NavigationManager(Context ctx){
        this.ctx=ctx;

    }


    private static void goTo(Context ctx, Class<?> cls){
        Intent dst=new Intent(ctx, cls);

        ctx.startActivity(dst);

    }

    public  static void Login(Context ctx){
        goTo(ctx, LoginActivity.class);
    }

    public  static void Home(Context ctx){
        goTo(ctx, HomeActivity.class);
    }

    public  static void Register(Context ctx){
        goTo(ctx, RegisterActivity.class);
    }

    public  static void Main(Context ctx){
        goTo(ctx, MainActivity.class);
    }

    public  static void Profile(Context ctx){
        goTo(ctx, ProfileActivity.class);
    }

    public  static void Settings(Context ctx){
        goTo(ctx, SettingsActivity.class);
    }

    public  static void UpdateProfile(Context ctx){
        goTo(ctx, UpdateProfileActivity.class);
    }



    public void Home(){
        NavigationManager.Home(ctx);
    }

    public void Login(){
        NavigationManager.Login(ctx);
    }

    public void Profile(){
        NavigationManager.Profile(ctx);
    }

    public void Register(){
        NavigationManager.Register(ctx);
    }


}
