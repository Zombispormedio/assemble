package com.zombispormedio.assemble.adapters;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 03/11/2016.
 */

public class UserMessageHolder extends MessageHolder {

    @BindView(R.id.user_content_text)
    TextView userContent;


    @BindView(R.id.message_state)
    ImageView userImageView;


    public UserMessageHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_message, parent, false));
    }

    @Override
    protected void renderData(@NonNull Message message) {
        super.renderData(message);
        userContent.setText(message.content);
        renderUserMessageState(message);
    }

    private void renderUserMessageState(@NonNull Message message) {
        if (message.is_read) {
            userImageView.setImageResource(R.drawable.message_check_all_layer);
        } else if (message.is_sent) {
            userImageView.setImageResource(R.drawable.message_check_layer);
        } else {
            userImageView.setImageResource(R.drawable.message_clock_layer);
        }
    }



}
