package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.fragments.ConversationFragment;
import com.zombispormedio.assemble.fragments.MenuFormChatFragment;
import com.zombispormedio.assemble.fragments.MessageFormFragment;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.activities.IChatView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.MANY_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_READ_NOTIFY_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.FragmentStates.MENU_CHAT;
import static com.zombispormedio.assemble.utils.AndroidConfig.FragmentStates.MESSAGE_INPUT;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGE_ID;

public class ChatActivity extends BaseActivity implements IChatView {

    private ChatController ctrl;

    @BindView(R.id.image_view)
    ImageView imageView;

    private boolean fromNotification;

    private ConversationFragment conversationFragment;

    private MessageFormFragment messageFormFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupToolbar();
        bindActivity(this);

        int chatId = setupController();

        registerIDToMessagingService(chatId);

        conversationFragment= (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        setupForm();

        ctrl.onCreate();
    }



    private int setupController() {
        Intent intent = getIntent();
        String action = intent.getAction();
        Bundle extra = intent.getExtras();
        int chatId = extra.getInt(CHAT_ID);
        fromNotification = false;
        if (MANY_MESSAGE_ACTION.equals(action)) {
            ArrayList<Message> messages = extra.getParcelableArrayList(MESSAGES);
            ctrl = new ChatController(this, chatId, messages);
        } else {
            ctrl = new ChatController(this, chatId);
        }

        fromNotification = extra.getBoolean(FOREGROUND_NOTIFICATION);

        return chatId;
    }

    private void setupForm() {
        messageFormFragment=new MessageFormFragment();
        messageFormFragment.setSendButtonListener(ctrl::onMessageSend);
        messageFormFragment.setPlusButtonListener(this::openPlusMenu);

        goToMessageInput();
    }

    private void openPlusMenu() {
        MenuFormChatFragment fragment= new MenuFormChatFragment();
        fragment.setBackButtonListener(this::goToMessageInput);
        replaceInputContainer(fragment, MENU_CHAT);
    }

    private void replaceInputContainer(Fragment fragment, String tag){
        replaceUI(R.id.message_input_container, fragment, tag);
    }

    private void goToMessageInput(){
        replaceInputContainer( messageFormFragment, MESSAGE_INPUT);
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
    public int addPendingMessage(Message message) {
        return getConversation().addPendingMessage(message);
    }

    @Override
    public void updateMessage(int index, Message message) {
        getConversation().updateMessage(index, message);
    }

    @Override
    public void addMessage(Message message) {
        getConversation().addMessage(message);
    }

    @Override
    public void read(int id) {
        getConversation().read(id);
    }

    @Override
    public void bindMessages(ArrayList<Message> messages) {
        getConversation().bindMessages(messages);
    }


    protected ConversationFragment getConversation() {
        return  conversationFragment;
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
                    getConversation().read(id);
                }
            }

        }
    }


}
