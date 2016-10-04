package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.LeftListAdapter;
import com.zombispormedio.assemble.adapters.lists.RightListAdapter;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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

public class MessageHolder extends AbstractHolder<Message.Container> {

    @BindView(R.id.friend_layout)
    LinearLayout friendLayout;

    @BindView(R.id.user_layout)
    FrameLayout userLayout;

    @BindView(R.id.friend_messages_list)
    RecyclerView friendMessagesList;

    @BindView(R.id.user_messages_list)
    RecyclerView userMessagesList;

    @BindView(R.id.friend_image_view)
    ImageView friendImageView;

    public MessageHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
        AndroidUtils.createListConfiguration(itemView.getContext(), friendMessagesList)
                .itemAnimation(true)
                .configure();
        AndroidUtils.createListConfiguration(itemView.getContext(), userMessagesList)
                .itemAnimation(true)
                .configure();
    }

    @Override
    public void bind(int position, Message.Container message) {
        if(message.getSender() instanceof UserProfile){
            bindUserMessage(message);
        }else{
            bindFriendMessage(message);
        }
    }

    private void bindFriendMessage(Message.Container message) {
        showFriend();
        Profile sender=message.getSender();
        setupFriendImage(sender.medium_avatar_url, StringUtils.firstLetter(sender.username));

        LeftListAdapter adapter=new LeftListAdapter();

        adapter.setData(message.getMessages());

        friendMessagesList.setAdapter(adapter);

    }

    private void setupFriendImage(String path, String letter) {
        new ImageUtils.ImageBuilder(itemView.getContext(), friendImageView)
                .url(path)
                .letter(letter)
                .circle(true)
                .build();
    }

    private void bindUserMessage(Message.Container message) {
        showUser();
        RightListAdapter adapter=new RightListAdapter();

        adapter.setData(message.getMessages());

        userMessagesList.setAdapter(adapter);
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
