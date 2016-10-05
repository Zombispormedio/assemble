package com.zombispormedio.assemble.models;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class Message extends ContentMessage{

    public Profile sender;

    public Profile recipient;

    public Message(int id, String content, boolean is_read, boolean is_sent, boolean is_delivered, String created_at,
            Profile sender, Profile recipient) {
        super(id, content, is_read, is_sent, is_delivered, created_at);
        this.sender = sender;
        this.recipient = recipient;
    }

    public Message(String content, Profile sender) {
        super(0, content, false, false, false, null);
        this.sender = sender;
    }



}
