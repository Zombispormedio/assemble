package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class Message extends BaseModel{

    public String created_at;

    public UserProfile sender;

    public FriendProfile recipient;

    public String content;

    public boolean is_read;

    public boolean is_sent;

    public boolean is_delivered;

    public Message() {
        super(0);
    }

    public Message(int id, String created_at, UserProfile sender, FriendProfile recipient, String content, boolean is_read,
            boolean is_sent, boolean is_delivered) {
        super(id);
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.is_read = is_read;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
    }
}
