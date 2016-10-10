package com.zombispormedio.assemble.models;


/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class Message extends ContentMessage{

    public int sender_id;

    public int recipient_id;

    public int chat_id;

    public Profile sender;

    public Profile recipient;

    public Message(int id, String content, boolean is_read, boolean is_sent, boolean is_delivered, String created_at,
            Profile sender, Profile recipient, int chat_id) {
        super(id, content, is_read, is_sent, is_delivered, created_at);
        this.sender = sender;
        this.recipient = recipient;
        this.chat_id=chat_id;
    }

    public Message(String content, Profile sender) {
        super(0, content, false, false, false, null);
        this.sender = sender;
    }



}
