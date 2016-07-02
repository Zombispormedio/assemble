package com.zombispormedio.assemble;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Master on 25/06/2016.
 */
public final class Navigation {
    private Context ctx;
    private Type type;

    public Navigation(Context ctx){
        this.ctx=ctx;
        this.type=Type.MAIN;
    }

    public Navigation(Context ctx, Type type){
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

    public void goHome(){
        Navigation.Home(ctx);
    }

    public void  goToLogin(){
        Navigation.Login(ctx);
    }

    public void Combo(boolean statement){
        switch (type){
            case MAIN:{
                if(statement){
                    goHome();
                }else{goToLogin();}
                break;
            }
            case LOGIN:{
                if(statement){
                    goHome();
                }
                break;
            }

            case HOME:{
                if(!statement){
                    goToLogin();
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
