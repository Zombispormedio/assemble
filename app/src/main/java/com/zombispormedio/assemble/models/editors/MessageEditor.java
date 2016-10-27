package com.zombispormedio.assemble.models.editors;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageEditor {

    public final String content;

    public MessageEditor(String content) {
        this.content = content;
    }

    public static class Builder{
        private String content;

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public MessageEditor build(){
            return new MessageEditor(content);
        }
    }

}
