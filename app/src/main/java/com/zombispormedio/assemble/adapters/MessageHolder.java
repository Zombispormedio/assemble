package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageHolder extends AbstractHolder<Message> {

    @BindView(R.id.friend_layout)
    LinearLayout friendLayout;

    @BindView(R.id.user_layout)
    FrameLayout userLayout;

    @BindView(R.id.friend_image_view)
    ImageView friendImageView;

    @BindView(R.id.friend_content_text)
    TextView friendContent;

    @BindView(R.id.user_content_text)
    TextView userContent;

    @BindView(R.id.message_state)
    ImageView userImageView;

    private boolean haveSiblings;

    public MessageHolder(View itemView) {
        super(itemView);
        haveSiblings=false;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Message message) {
        renderData(message);
    }

    public void bind(int position, Message message, Message previous) {
        registerIfHaveSiblings(message, previous);
        renderData(message);
    }

    public void renderData(Message message) {

        if (message.sender instanceof UserProfile) {
            renderUserMessage(message);
        } else {
            renderFriendMessage(message);
        }
    }

    private void renderFriendMessage(Message message) {
        showFriend();
        
        friendContent.setText(message.content);
        
        if (haveSiblings) {
            friendImageView.setVisibility(View.INVISIBLE);
        } else {
            doIfFriendMessageHaveSiblings(message);
        }

    }

    private void doIfFriendMessageHaveSiblings(Message message) {
        friendImageView.setVisibility(View.VISIBLE);
        message.sender.getMediumImageBuilder()
                .context(getContext())
                .imageView(friendImageView)
                .build();
    }

    private void renderUserMessage(Message message) {
        showUser();
        userContent.setText(message.content);
        renderUserMessageState(message);
    }

    private void renderUserMessageState(Message message) {
        if (message.is_read)
            userImageView.setImageResource(R.drawable.message_check_all_layer);
        else if (message.is_sent)
            userImageView.setImageResource(R.drawable.message_check_layer);
    }

    private void showFriend() {
        friendLayout.setVisibility(View.VISIBLE);
        userLayout.setVisibility(View.GONE);
    }


    private void showUser() {
        userLayout.setVisibility(View.VISIBLE);
        friendLayout.setVisibility(View.GONE);
    }

    private void registerIfHaveSiblings(Message that, Message previous){
        haveSiblings=that.sender.id==previous.sender.id;
    }
    
}
