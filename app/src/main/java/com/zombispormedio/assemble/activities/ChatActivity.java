package com.zombispormedio.assemble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.MessageListAdapter;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements IChatView {

    public static final String CHAT_ID = "chat_id";

    public static final String NEW_MESSAGE_ACTION = "NEW_MESSAGE_ACTION";

    private ChatController ctrl;

    @BindView(R.id.messages_list)
    RecyclerView messagesList;

    @BindView(R.id.image_view)
    ImageView imageView;

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
        int chatId;
        Bundle extra = intent.getExtras();

        if (NEW_MESSAGE_ACTION.equals(action)) {
            HashMap<String, String> message = AndroidUtils.convertBundleToStringHashMap(extra);
            ctrl = new ChatController(this, message);
            chatId = Integer.parseInt(message.get(CHAT_ID));
            fromNotification = true;
        } else {
            chatId = extra.getInt(NavigationManager.ARGS + 0);
            ctrl = new ChatController(this, chatId);
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
        PreferencesManager preferencesManager = getPreferencesManager();
        preferencesManager.set(AssembleApplication.RUNNING_ACTIVITY, getClass().getName());
        preferencesManager.set(CHAT_ID, dataId);
    }

    private void unregisterIDToMessagingService() {
        PreferencesManager preferencesManager = getPreferencesManager();
        preferencesManager.remove(AssembleApplication.RUNNING_ACTIVITY);
        preferencesManager.remove(CHAT_ID);
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
            // Respond to the action bar's Up/Home button
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
            // This activity is NOT part of this app's task, so create a new task
            // when navigating up, with a synthesized back stack.
            TaskStackBuilder.create(this)
                    // Add all of this activity's parents to the back stack
                    .addNextIntentWithParentStack(upIntent)
                    // Navigate up to the closest parent
                    .startActivities();
        } else {
            // This activity is part of this app's task, so simply
            // navigate up to the logical parent activity.
            NavUtils.navigateUpTo(this, upIntent);
        }
    }

}
