package com.zombispormedio.assemble.models.editors;

/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class ChatEditor {

    public final int friend;

    public final int[] messages;

    public ChatEditor(int friend, int[] messages) {
        this.friend = friend;
        this.messages = messages;
    }


    public static class Builder{
        private int friend;

        private int[] messages;

        public Builder setFriend(int friend) {
            this.friend = friend;
            return this;
        }

        public Builder setMessages(int[] messages) {
            this.messages = messages;
            return this;
        }

        public ChatEditor build(){
            return new ChatEditor(friend, messages);
        }
    }

}
