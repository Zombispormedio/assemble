package com.zombispormedio.assemble.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.MessageListAdapter;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.NEW_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;

public class ChatActivity extends BaseActivity implements IChatView {

    private ChatController ctrl;

    @BindView(R.id.messages_list)
    RecyclerView messagesList;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.message_input)
    EditText messageInput;

    private MessageListAdapter messageListAdapter;

    private boolean fromNotification;

    private MessageReceiver messageReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupToolbar();
        bindActivity(this);
        fromNotification = false;
        int chatId = setupController();

        registerIDToMessagingService(chatId);

        setupMessages();

        ctrl.onCreate();
    }

    private int setupController() {
        Intent intent = getIntent();
        String action = intent.getAction();
        int chatId;
        Bundle extra = intent.getExtras();

        if (NEW_MESSAGE_ACTION.equals(action)) {
            HashMap<String, String> message = AndroidUtils.convertBundleToStringHashMap(extra);
            ctrl = new ChatController(this, message);
            chatId = Integer.parseInt(message.get(CHAT_ID));
            fromNotification = true;
        } else {
            String rawChatId=extra.getString(CHAT_ID);
            chatId = Integer.parseInt(rawChatId);
            ctrl = new ChatController(this, chatId);
            fromNotification=extra.getBoolean(FOREGROUND_NOTIFICATION);
        }

        return chatId;
    }


    private void setupMessages() {
        AndroidUtils.createListConfiguration(this, messagesList)
                .startAtEnd(true)
                .configure();

        RecyclerView.ItemAnimator animator = messagesList.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        messageListAdapter = new MessageListAdapter();

        messagesList.setAdapter(messageListAdapter);
    }


    @Override
    public void bindTitle(String title) {
        setTitle(title);
    }

    @Override
    public void setAvatar(ImageUtils.ImageBuilder builder) {
        builder.context(this)
                .imageView(imageView)
                .build();
    }

    @Override
    public void bindMessages(ArrayList<Message> messages) {
        messageListAdapter.setData(messages);
        messagesList.scrollToPosition(messages.size()-1);
    }


    @Override
    public String getMessageInputValue() {
        String value = messageInput.getText().toString();
        messageInput.setText("");
        return value;
    }

    @Override
    public int addPendingMessage(Message message) {
        int index = messageListAdapter.addPending(message);
        messagesList.scrollToPosition(index);
        return index;
    }

    @Override
    public void addMessage(int index, Message message) {
        messageListAdapter.checkMessage(index, message);
    }

    @OnClick(R.id.send_button)
    public void onSend() {
        ctrl.onMessageSend();
    }


    private void registerIDToMessagingService(int dataId) {
        getPreferencesManager().set(CHAT_ID, dataId);
    }


    private void unregisterIDToMessagingService() {
        getPreferencesManager().remove(CHAT_ID);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
        unregisterIDToMessagingService();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    private void goBack() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (fromNotification) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }


    @Override
    protected void setupReceivers() {
        super.setupReceivers();
        messageReceiver=new MessageReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(ON_MESSAGE_NOTIFY_CHAT);
        registerReceiver(messageReceiver, intentFilter);
    }

    @Override
    protected void slashReceivers() {
        super.slashReceivers();
        unregisterReceiver(messageReceiver);
    }

    private class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getResourceComponent().provideMessageSubscription().haveChanged();
        }
    }


}
