package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Chat {

    public final String id;
    public final String created_at;
    public final Sender sender;
    public final Recipient recipient;

    public Chat(String id, String created_at, Sender sender, Recipient recipient) {
        this.id = id;
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
    }

}
