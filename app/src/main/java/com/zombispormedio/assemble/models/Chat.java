package com.zombispormedio.assemble.models;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Chat extends BaseModel{

    public final String created_at;

    public final UserProfile sender;

    public final FriendProfile recipient;

    public final Message[] messages;

    public int owner_id;

    public int friend_id;

    public Chat(int id, String created_at, UserProfile sender, FriendProfile recipient,
            Message[] messages) {
        super(id);
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
        this.messages = messages;
    }

    public ArrayList<Message> getMessages(){
        return new ArrayList<>(Arrays.asList(messages));
    }
}
