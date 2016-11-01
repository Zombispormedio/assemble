package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.MessageListAdapter;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.MANY_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_READ_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_ID;

public class ChatActivity extends BaseActivity implements IChatView {

    @Nullable
    private ChatController ctrl;

    @Nullable
    @BindView(R.id.messages_list)
    RecyclerView messagesList;

    @Nullable
    @BindView(R.id.image_view)
    ImageView imageView;

    @Nullable
    @BindView(R.id.message_input)
    EditText messageInput;

    private MessageListAdapter messageListAdapter;

    private boolean fromNotification;


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
        Bundle extra = intent.getExtras();
        int chatId = extra.getInt(CHAT_ID);

        if (MANY_MESSAGE_ACTION.equals(action)) {
            ArrayList<Message> messages = extra.getParcelableArrayList(MESSAGES);
            ctrl = new ChatController(this, chatId, messages);
        } else {
            ctrl = new ChatController(this, chatId);
        }

        fromNotification = extra.getBoolean(FOREGROUND_NOTIFICATION);

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
        setToolbarTitle(title);
    }

    @Override
    public void setAvatar(@NonNull ImageUtils.ImageBuilder builder) {
        builder.context(this)
                .imageView(imageView)
                .build();
    }

    @Override
    public void bindMessages(@NonNull ArrayList<Message> messages) {
        messageListAdapter.addAll(messages);
        messagesList.scrollToPosition(messages.size() - 1);
    }


    @NonNull
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
    public void updateMessage(int index, Message message) {
        messageListAdapter.checkMessage(index, message);
    }

    @Override
    public void addMessage(Message message) {
        int index = messageListAdapter.getItemCount();
        messageListAdapter.add(message);
        messagesList.scrollToPosition(index);
    }

    @Override
    public void read(int id) {
        messageListAdapter.read(id);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
        configureReceiver(new MessageReceiver(), ON_MESSAGE_NOTIFY_CHAT);
        configureReceiver(new ReadReceiver(), ON_READ_NOTIFY_CHAT);
    }


    private class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, @NonNull Intent intent) {

            getResourceComponent().provideMessageSubscription().haveOneChanged(intent.getExtras().getInt(MESSAGE_ID));
        }
    }

    private class ReadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            Bundle data = intent.getExtras();
            int[] messagesIds = data.getIntArray(MESSAGES);
            if (messagesIds != null) {
                for (int id : messagesIds) {
                    read(id);
                }
            }

        }
    }


}
