package com.zombispormedio.assemble.net;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class State {

    private static State ourInstance = new State();

    public static State getInstance() {
        return ourInstance;
    }

    private Context ctx;

    private State() {
    }

    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

    public boolean isConnected(){
        boolean haveConnection=false;
        if(ctx!=null){
            ConnectivityManager cm=(ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork= cm.getActiveNetworkInfo();
            haveConnection=activeNetwork.isConnectedOrConnecting();
        }

        return haveConnection;
    }
}
