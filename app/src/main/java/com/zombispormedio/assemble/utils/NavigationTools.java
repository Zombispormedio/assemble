package com.zombispormedio.assemble.utils;

import android.content.Context;
import android.content.Intent;

import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.activities.LoginActivity;
import com.zombispormedio.assemble.activities.MainActivity;
import com.zombispormedio.assemble.activities.ProfileActivity;
import com.zombispormedio.assemble.activities.RegisterActivity;

/**
 * Created by Master on 25/06/2016.
 */
public final class NavigationTools {
    private Context ctx;
    private Type type;

    public NavigationTools(Context ctx){
        this.ctx=ctx;
        this.type=Type.MAIN;
    }

    public NavigationTools(Context ctx, Type type){
        this.ctx=ctx;
        this.type=type;
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


    public void Home(){
        NavigationTools.Home(ctx);
    }

    public void Login(){
        NavigationTools.Login(ctx);
    }

    public void Profile(){
        NavigationTools.Profile(ctx);
    }

    public void Combo(boolean statement){
        switch (type){
            case MAIN:{
                if(statement){
                    Home();
                }else{
                    Login();}
                break;
            }
            case LOGIN:{
                if(statement){
                    Home();
                }
                break;
            }

            case HOME:{
                if(!statement){
                    Login();
                }
                break;
            }
        }
    }

    public enum Type{
        MAIN,
        LOGIN,
        HOME
    }

}
