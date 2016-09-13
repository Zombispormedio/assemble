package com.zombispormedio.assemble.models.subscriptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class Subscription {

    private HashMap<String, Subscriber> subscribers;


    public Subscription() {
        subscribers= new HashMap<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.put(subscriber.getID(), subscriber);
    }

    public void removeSubscriber(String id){
        subscribers.remove(id);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber.getID());
    }

    protected void notifySubscribers(){
        for(Map.Entry<String, Subscriber> entry : subscribers.entrySet()){
            Subscriber subscriber=entry.getValue();
            subscriber.notifyChange();
        }
    }
}
