package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 03/11/2016.
 */

public class FriendMessageWithImageHolder extends FriendMessageHolder{

    @BindView(R.id.friend_image_view)
    ImageView friendImageView;

    public FriendMessageWithImageHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friend_message_with_image, parent, false));
    }

    @Override
    protected void renderData(@NonNull Message message) {
        super.renderData(message);
        message.sender.getMediumImageBuilder()
                .context(getContext())
                .imageView(friendImageView)
                .build();
    }
}
