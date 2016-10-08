package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.MessageListAdapter;
import com.zombispormedio.assemble.controllers.ChatController;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IChatView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements IChatView {

    private ChatController ctrl;

    @BindView(R.id.messages_list)
    RecyclerView messagesList;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.message_input)
    EditText messageInput;

    private MessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupToolbar();
        bindActivity(this);

        Bundle extra=getIntent().getExtras();

        ctrl = new ChatController(this, extra.getInt(NavigationManager.ARGS+0));

        setupMessages();

        ctrl.onCreate();
    }

    private void setupMessages() {
        AndroidUtils.createListConfiguration(this, messagesList)
                .startAtEnd(true)
                .configure();

        RecyclerView.ItemAnimator animator = messagesList.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        messageListAdapter=new MessageListAdapter();

        messagesList.setAdapter(messageListAdapter);
    }


    @Override
    public void bindTitle(String title) {
        setTitle(title);
    }

    @Override
    public void setAvatar(String path, String letter) {
        new ImageUtils.ImageBuilder(this, imageView)
                .url(path)
                .letter(letter)
                .circle(true)
                .build();
    }

    @Override
    public void bindMessages(ArrayList<Message> messages) {
        messageListAdapter.setData(messages);
    }



    @Override
    public String getMessageInputValue() {
        String value=messageInput.getText().toString();
        messageInput.setText("");
        return value;
    }

    @Override
    public int addPendingMessage(Message message) {
        int index=messageListAdapter.addPending(message);
        messagesList.scrollToPosition(index);
        return index;
    }

    @Override
    public void addMessage(int index, Message message) {
        messageListAdapter.checkMessage(index, message);
    }

    @OnClick(R.id.send_button)
    public void onSend(){
        ctrl.onMessageSend();
    }
}