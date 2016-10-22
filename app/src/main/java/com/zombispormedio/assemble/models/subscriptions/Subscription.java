package com.zombispormedio.assemble.models.subscriptions;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class Subscription {

    private final HashMap<String, Subscriber> subscribers;


    public Subscription() {
        subscribers = new HashMap<>();
    }

    public Subscription addSubscriber(Subscriber subscriber) {
        subscribers.put(subscriber.getID(), subscriber);
        return this;
    }

    public Subscription removeSubscriber(String id) {
        subscribers.remove(id);
        return this;
    }

    public Subscription removeSubscriber(Subscriber subscriber) {
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

    private ArrayList<Subscriber> getSubscribers() {
        return new ArrayList<>(subscribers.values());
    }

}
