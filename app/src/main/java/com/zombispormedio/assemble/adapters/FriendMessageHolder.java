package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 03/11/2016.
 */

public class FriendMessageHolder extends MessageHolder {




    @BindView(R.id.friend_content_text)
    TextView friendContent;

    public FriendMessageHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friend_message, parent, false));
    }

    public FriendMessageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void renderData(@NonNull Message message) {
        super.renderData(message);
        friendContent.setText(message.content);
    }

}
