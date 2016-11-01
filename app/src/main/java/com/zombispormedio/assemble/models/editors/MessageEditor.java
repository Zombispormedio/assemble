package com.zombispormedio.assemble.models.editors;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageEditor {

    public final String content;

    public MessageEditor(String content) {
        this.content = content;
    }

    public static class Builder {

        private String content;

        @NonNull
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        @NonNull
        public MessageEditor build() {
            return new MessageEditor(content);
        }
    }

}
