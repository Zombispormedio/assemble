package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatHolder extends AbstractHolder<Chat> {


    @BindView(R.id.username_label)
    TextView nameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    private IOnClickItemListHandler<Chat> listener;

    public ChatHolder(View view) {
        super(view);
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Chat itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Chat itemData) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Chat itemData) {
        FriendProfile recipient=itemData.recipient;

        String recipientName=recipient.username;
        nameLabel.setText(recipientName);

        new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .url(recipient.large_avatar_url)
                .letter(StringUtils.firstLetter(recipientName))
                .circle(true)
                .build();
    }

    public void setOnClickListener(IOnClickItemListHandler<Chat> listener) {
        this.listener = listener;
    }
}
