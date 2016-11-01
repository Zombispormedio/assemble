package com.zombispormedio.assemble.models.subscriptions;

import com.zombispormedio.assemble.handlers.SuccessHandler;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 14/09/2016.
 */
public class DataSubscription extends Subscription implements IDataSubscription {

    @Override
    public void load() {
    }

    @NonNull
    protected SuccessHandler deferSubscribers() {
        return new SuccessHandler() {
            @Override
            public void onSuccess() {
                notifySubscribers();
            }

            @Override
            public void onError() {
                notifyFailToSubscribers();
            }
        };
    }
}
