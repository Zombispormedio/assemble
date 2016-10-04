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

    public static class Container{
        private Profile sender;

        private ArrayList<ContentMessage> messages;

        public Container(Profile sender) {
            this.sender = sender;
            messages=new ArrayList<>();
        }

        public void addMessage(ContentMessage message){
            messages.add(message);
        }

        public void changeMessage(int index, ContentMessage message){
            messages.set(index, message);
        }

        public Profile getSender() {
            return sender;
        }

        public ArrayList<ContentMessage> getMessages() {
            return messages;
        }
    }


}
