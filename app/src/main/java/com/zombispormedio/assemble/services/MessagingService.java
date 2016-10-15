package com.zombispormedio.assemble.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.utils.RunningActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_EVENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_EVERYWERE;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Codes.NOTIFICATION_DEFAULT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CONTENT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_BUNDLE;


public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Message message= convertMessage((ArrayMap<String, String>) remoteMessage.getData());
        saveMessage(message);
        int currentChatID=getPreferencesManager().getInt(CHAT_ID);

        String who=RunningActivity.whoIsRunning;

        if(who.equals(ChatActivity.class.getName())){
            notifyChat(message, currentChatID);

        }else if(who.equals(HomeActivity.class.getName())){
            notifyHome();
        }else {
            notifyEveryWhere(message);
        }


    }



    private Message convertMessage(ArrayMap<String, String> data) {
        HashMap<String, String> hashMap=AndroidUtils.convertArrayMapToHashMap(data);
        return Message.createMessage(hashMap);
    }

    private void saveMessage(Message message) {
        Intent intent=new Intent();
        intent.setAction(ON_MESSAGE_EVENT);
        intent.putExtra(MESSAGE_BUNDLE, message);
        sendBroadcast(intent);
    }



    private void notifyHome() {
        Intent intent=new Intent();
        intent.setAction(ON_MESSAGE_NOTIFY_HOME);
        sendBroadcast(intent);
    }

    private void notifyChat(Message message, int currentChatID) {
        if (message.chat_id==currentChatID){
            Intent intent=new Intent();
            intent.setAction(ON_MESSAGE_NOTIFY_CHAT);
            sendBroadcast(intent);
        }else{
            notifyEveryWhere(message);
        }
    }

    private void notifyEveryWhere(Message message) {
        showMessageNotification(String.valueOf(message.chat_id), message.content);
    }


    protected PreferencesManager getPreferencesManager() {
        return ((AssembleApplication)getApplication()).getPreferencesManager();
    }

    private void showMessageNotification(String chatId, String content) {
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.logo)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .setContentTitle(getString(R.string.new_message_title))
                .setContentText(content)
                .setSound(soundUri)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[] {1, 1, 1})
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true);

        Intent resultIntent = new Intent(getApplicationContext(), ChatActivity.class);
        resultIntent.putExtra(CHAT_ID, chatId);
        resultIntent.putExtra(FOREGROUND_NOTIFICATION, true);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, mBuilder.build());
    }



}
