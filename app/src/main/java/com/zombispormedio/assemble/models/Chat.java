package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Chat extends BaseModel{

    public int id;

    public String created_at;

    public Sender sender;

    public Recipient recipient;

    public Chat() {
        created_at="";
    }

    public Chat(int id, String created_at, Sender sender, Recipient recipient) {
        this.id = id;
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
    }
}
