package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Chat extends BaseModel{

    public String created_at;

    public UserProfile sender;

    public FriendProfile recipient;

    public Chat() {
        super(0);
        created_at="";
    }

    public Chat(int id, String created_at, UserProfile sender, FriendProfile recipient) {
        super(id);
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
    }
}
