package com.zombispormedio.assemble.net;

import com.zombispormedio.assemble.views.IApplicationView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ConnectionState {

    private static final ConnectionState ourInstance = new ConnectionState();

    @NonNull
    public static ConnectionState getInstance() {
        return ourInstance;
    }

    @Nullable
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
