package com.zombispormedio.assemble.net;

import com.zombispormedio.assemble.IAssembleApplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ConnectionState {

    private static ConnectionState ourInstance = new ConnectionState();

    public static ConnectionState getInstance() {
        return ourInstance;
    }

    private IAssembleApplication ctx;

    private ConnectionState() {
    }

    public void setContext(IAssembleApplication ctx) {
        this.ctx = ctx;
    }

    public boolean isConnected(){
        return ctx.isConnected();
    }

    public void onTerminate(){
        ctx=null;
    }

}
