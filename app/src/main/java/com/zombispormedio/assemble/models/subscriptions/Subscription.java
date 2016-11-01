package com.zombispormedio.assemble.models.subscriptions;

import com.annimon.stream.Stream;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class Subscription {

    @NonNull
    private final HashMap<String, Subscriber> subscribers;


    public Subscription() {
        subscribers = new HashMap<>();
    }

    @NonNull
    public Subscription addSubscriber(@NonNull Subscriber subscriber) {
        subscribers.put(subscriber.getID(), subscriber);
        return this;
    }

    @NonNull
    public Subscription removeSubscriber(String id) {
        subscribers.remove(id);
        return this;
    }

    @NonNull
    public Subscription removeSubscriber(@NonNull Subscriber subscriber) {
        subscribers.remove(subscriber.getID());
        return this;
    }

    protected void notifySubscribers() {
        Stream.of(getSubscribers())
                .forEach(Subscriber::notifyChange);
    }

    protected void notifyFailToSubscribers() {

        Stream.of(getSubscribers())
                .forEach(Subscriber::notifyFail);
    }

    public void haveOneChanged(int id) {
        Stream.of(getSubscribers())
                .forEach((entry) -> entry.notifyOneChange(id));
    }

    public void haveChanged() {
        notifySubscribers();
    }

    public void haveFailed() {
        notifyFailToSubscribers();
    }

    @NonNull
    private ArrayList<Subscriber> getSubscribers() {
        return new ArrayList<>(subscribers.values());
    }

}
