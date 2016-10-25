package com.zombispormedio.assemble.utils;

/**
 * Created by Xavier Serrano on 15/10/2016.
 */

public class AndroidConfig {

    public static class Keys {

        public static final String CHAT_ID = "chat_id";

        public static final String AUTH = "token";

        public final static String STATE = "state";

        public final static String LOADED = "loaded";

        public static final String MESSAGING_ID = "messaging:id";

        public static final String MESSAGE_BUNDLE="message:bundle";

        public static final String FOREGROUND_NOTIFICATION ="foreground_notification";

        public static final String CONTENT="content";
    }


    public static class Actions {

        public static final String NEW_MESSAGE_ACTION = "NEW_MESSAGE_ACTION";

        public static final String ON_MESSAGE_EVENT = "ON_MESSAGE_EVENT";

        public static final String ON_MESSAGE_NOTIFY_CHAT = " ON_MESSAGE_NOTIFY_CHAT";

        public static final String ON_MESSAGE_NOTIFY_HOME = "ON_MESSAGE_NOTIFY_HOME";

        public static final String ON_MESSAGE_NOTIFY_EVERYWHERE = "ON_MESSAGE_NOTIFY_EVERYWHERE";
    }

    public static class Groups{
        public static  final String Message="message";
    }



}
