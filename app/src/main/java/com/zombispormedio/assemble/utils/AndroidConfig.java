package com.zombispormedio.assemble.utils;

/**
 * Created by Xavier Serrano on 15/10/2016.
 */

public class AndroidConfig {

    public static class Keys {

        public static final String CHAT_ID = "chat_id";

        public static final String MESSAGE_ID = "message_id";

        public static final String AUTH = "token";

        public final static String STATE = "state";

        public final static String LOADED = "loaded";

        public static final String MESSAGE_BUNDLE = "message:bundle";

        public static final String FOREGROUND_NOTIFICATION = "foreground_notification";

        public static final String MESSAGES = "messages";


        public static final String READ = "read";
    }


    public static class Actions {

        public static final String ON_MESSAGE_EVENT = "ON_MESSAGE_EVENT";

        public static final String ON_MESSAGE_NOTIFY_CHAT = " ON_MESSAGE_NOTIFY_CHAT";

        public static final String ON_MESSAGE_NOTIFY_HOME = "ON_MESSAGE_NOTIFY_HOME";

        public static final String ONE_MESSAGE_ACTION = "ONE_MESSAGE_ACTION";

        public static final String MANY_MESSAGE_ACTION = "MANY_MESSAGE_ACTION";

        public static final String SEVERAL_MESSAGE_ACTION = "SEVERAL_MESSAGE_ACTION";

        public static final String SEVERAL_MESSAGE_ACTIVE_ACTION = "SEVERAL_MESSAGE_ACTIVE_ACTION";

        public static final String ON_READ_EVENT = "ON_READ_EVENT";

        public static final String ON_READ_NOTIFY_CHAT = "ON_READ_EVENT";
    }

    public static class Groups {

        public static final String MESSAGE_GROUP = "message";

        public static final String READ_GROUP = "read_message";
    }


}
