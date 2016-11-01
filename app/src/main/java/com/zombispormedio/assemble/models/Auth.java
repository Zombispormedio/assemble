package com.zombispormedio.assemble.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Auth {

    public final String email;

    public final String password;

    @Nullable
    public final String gcm_token;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
        gcm_token = null;
    }

    public Auth(String email, String password, String gcm_token) {
        this.email = email;
        this.password = password;
        this.gcm_token = gcm_token;
    }

    public static class Builder {

        private String email;

        private String password;

        private String gcmToken;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getGcmToken() {
            return gcmToken;
        }

        @NonNull
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @NonNull
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @NonNull
        public Builder setGcmToken(String gcmToken) {
            this.gcmToken = gcmToken;
            return this;
        }

        @NonNull
        public Auth build() {
            return new Auth(email, password, gcmToken);
        }
    }

}
