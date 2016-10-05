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

    public MessageHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Message message) {
        bindData(message, false);
    }

    public void bind(int position, Message message, Message previous) {
        boolean haveSiblings=message.sender.id==previous.sender.id;
        bindData(message, haveSiblings);
    }

    public void bindData(Message message, boolean haveSiblings){

        if(message.sender instanceof UserProfile){
            bindUserMessage(message);
        }else{
            bindFriendMessage(message, haveSiblings);
        }
    }

    private void bindFriendMessage(Message message, boolean haveSiblings) {
        showFriend();
        friendContent.setText(message.content);
        if(haveSiblings){
            friendImageView.setVisibility(View.INVISIBLE);
        }else{
            friendImageView.setVisibility(View.VISIBLE);
            Profile sender=message.sender;
            setupFriendImage(sender.medium_avatar_url, StringUtils.firstLetter(sender.username));
        }

    }

    private void setupFriendImage(String path, String letter) {
        new ImageUtils.ImageBuilder(itemView.getContext(), friendImageView)
                .url(path)
                .letter(letter)
                .circle(true)
                .build();
    }

    private void bindUserMessage(Message message) {
        showUser();
        userContent.setText(message.content);
        if(message.is_read){
            userImageView.setImageResource(R.drawable.message_check_all_layer);
        }else{
            if(message.is_sent){
                userImageView.setImageResource(R.drawable.message_check_layer);
            }
        }
    }

    private void showFriend(){
        friendLayout.setVisibility(View.VISIBLE);
        userLayout.setVisibility(View.GONE);
    }


    private void showUser(){
        userLayout.setVisibility(View.VISIBLE);
        friendLayout.setVisibility(View.GONE);
    }


}
