package com.zombispormedio.assemble.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.activities.HelpActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.activities.LoginActivity;
import com.zombispormedio.assemble.activities.MainActivity;
import com.zombispormedio.assemble.activities.ProfileActivity;
import com.zombispormedio.assemble.activities.RegisterActivity;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.activities.UpdateBirthdateActivity;
import com.zombispormedio.assemble.activities.UpdateProfileActivity;

/**
 * Created by Xavier Serrano on 25/06/2016.
 */
public final class NavigationManager {
    private Context ctx;

    public static final int UPDATE_BIRTHDATE_CODE=5956;

    public static final String ARGS ="args";

    public static final String SIZE ="size";

    public NavigationManager(Context ctx){
        this.ctx=ctx;

    }


    public static void goTo(Context ctx, Class<?> cls){
        Intent dst=new Intent(ctx, cls);
        ctx.startActivity(dst);

    }

    public static void goToWithResult(Activity ctx, Class<?> cls){
        Intent dst=new Intent(ctx, cls);
        ctx.startActivityForResult(dst, UPDATE_BIRTHDATE_CODE);

    }

    public static void goToWithResult(Activity ctx, Class<?> cls, String...extras){
        Intent dst=new Intent(ctx, cls);
        dst.putExtra(SIZE,extras.length);

        for(int i=0; i<extras.length; i++){
            dst.putExtra(ARGS +i,extras[i]);
        }
        ctx.startActivityForResult(dst, UPDATE_BIRTHDATE_CODE);

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

    public  static void Friends(Context ctx){
        goTo(ctx, FriendsActivity.class);
    }

    public  static void Help(Context ctx){
        goTo(ctx, HelpActivity.class);
    }

    public  static void UpdateBirthdate(Activity ctx){
        goToWithResult(ctx, UpdateBirthdateActivity.class);
    }

    public  static void UpdateBirthdate(Activity ctx, String... extras){
        goToWithResult(ctx, UpdateBirthdateActivity.class, extras);
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

    public void Settings(){
        NavigationManager.Settings(ctx);
    }

    public void Friends(){
        NavigationManager.Friends(ctx);
    }

    public void Help(){
        NavigationManager.Help(ctx);
    }

    public static void finishWithResult(Activity ctx, String... args){
        Intent intent = new Intent();
        intent.putExtra(SIZE,args.length);

        for(int i=0; i<args.length; i++){
            intent.putExtra(ARGS +i,args[i]);
        }
        ctx.setResult(ctx.RESULT_OK, intent);
        ctx.finish();

    }

}
