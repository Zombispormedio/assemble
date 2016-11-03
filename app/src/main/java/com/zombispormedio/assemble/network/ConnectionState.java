package com.zombispormedio.assemble.network;

import com.zombispormedio.assemble.views.IApplicationView;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ConnectionState {

    private static final ConnectionState ourInstance = new ConnectionState();

    @NonNull
    public static ConnectionState getInstance() {
        return ourInstance;
    }


    private IApplicationView ctx;

    private ConnectionState() {
    }

    public void setContext(IApplicationView ctx) {
        this.ctx = ctx;
    }

    public boolean isConnected() {
        return ctx.isConnected();
    }

    public void onTerminate() {
        ctx = null;
    }

}
