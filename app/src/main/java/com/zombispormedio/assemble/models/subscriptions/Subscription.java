package com.zombispormedio.assemble.models.subscriptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class Subscription{

    private HashMap<String, Subscriber> subscribers;


    public Subscription() {
        subscribers= new HashMap<>();
    }

    public Subscription addSubscriber(Subscriber subscriber){
        subscribers.put(subscriber.getID(), subscriber);
        return this;
    }

    public Subscription removeSubscriber(String id){
        subscribers.remove(id);
        return this;
    }

    public Subscription removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber.getID());
        return this;
    }

    protected void notifySubscribers(){
        HashMap<String, Subscriber> copy=new HashMap<>(subscribers);
        for(Map.Entry<String, Subscriber> entry : copy.entrySet()){
            if(entry!=null){
                Subscriber subscriber=entry.getValue();
                if(subscriber!=null){
                    subscriber.notifyChange();
                }
            }

        }
    }

}
