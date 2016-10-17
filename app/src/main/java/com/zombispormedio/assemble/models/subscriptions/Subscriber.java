package com.zombispormedio.assemble.models.subscriptions;

import java.util.UUID;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class Subscriber {

    private String uuid;


    public Subscriber() {
        uuid= UUID.randomUUID().toString();
    }

    public String getID() {
        return uuid;
    }

    public void notifyChange(){

    }

    public void notifyFail(){

    }

    public void notifyOneChange(int id){
        
    }
}
