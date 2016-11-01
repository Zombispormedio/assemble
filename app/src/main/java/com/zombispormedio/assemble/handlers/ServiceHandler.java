package com.zombispormedio.assemble.handlers;

import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ServiceHandler<T, E> implements IServiceHandler<T, E> {

    @Nullable
    private IServiceHandler<T, E> init;

    public ServiceHandler(IServiceHandler<T, E> init) {
        this.init = init;
    }

    public ServiceHandler() {
        this.init = null;
    }

    @Override
    public void onError(E error) {
        if (init != null) {
            init.onError(error);
        }
    }

    @Override
    public void onSuccess(T result) {
        if (init != null) {
            init.onSuccess(result);
        }
    }

    @Override
    public void onNotConnected() {
        if (init != null) {
            init.onNotConnected();
        }
    }
}
